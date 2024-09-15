#!/bin/bash

set -e

function check_clean_repo() {
  local gitStatus
  gitStatus=$(git status --porcelain)
  if [[ -n "$gitStatus" ]]; then
      echo "There are uncommitted changes in the repository! Please commit or stash them before running this script."
      exit 1
  fi
}

function inc_minor_version() {
  local version="$1"

  IFS='.' read -r -a versionParts <<< "$version"

  versionParts[1]=$((versionParts[1] + 1))
  versionParts[2]=0

  local joinedParts
  joinedParts=$(IFS='.'; echo "${versionParts[*]}")
  echo "$joinedParts"
}

check_clean_repo

# Read version
version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Strip snapshot suffix
releaseVersion=${version%-SNAPSHOT}

# Set release version
mvn versions:set -DnewVersion="$releaseVersion" -DgenerateBackupPoms=false

# Commit release value
git commit -am "Release v$releaseVersion"
git tag "v$releaseVersion"

# Increment minor version
newVersion=$(inc_minor_version "$releaseVersion")
newSnapshotVersion="$newVersion-SNAPSHOT"

# Set snapshot version
mvn versions:set -DnewVersion="$newSnapshotVersion" -DgenerateBackupPoms=false
git commit -am "Raised snapshot version v$newSnapshotVersion"

echo "Release tag created successfully!"

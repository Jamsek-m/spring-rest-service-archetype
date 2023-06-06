FROM amazoncorretto:17-alpine

RUN mkdir /app

WORKDIR /app

ADD ./v1/api/target/${artifactId}-v1.jar /app

ENV JAVA_ENV=PRODUCTION

EXPOSE 8080

CMD ["java", "-Xms128m", "-Xmx1024m", "-jar", "${artifactId}-v1.jar"]

package ${package}.persistence.common.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity extends RevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

}

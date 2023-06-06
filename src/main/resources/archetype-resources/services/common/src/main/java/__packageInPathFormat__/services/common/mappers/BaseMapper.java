package ${package}.services.common.mappers;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    default Instant fromOffsetDateTime(OffsetDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.toInstant();
        }
        return null;
    }

}

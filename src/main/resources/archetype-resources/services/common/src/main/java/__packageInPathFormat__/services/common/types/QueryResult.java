package ${package}.services.common.types;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QueryResult<T> {

    private final List<T> entities;

    private final Long count;

    private QueryResult(List<T> entities, long count) {
        this.entities = entities;
        this.count = count;
    }

    public static <T> QueryResult<T> of(List<T> entities, long count) {
        return new QueryResult<>(entities, count);
    }

    public static <T> QueryResult<T> of(List<T> entities) {
        return of(entities, entities.size());
    }

    public static <T> QueryResult<T> empty() {
        return of(new ArrayList<>());
    }
}

package ${package}.services.common.types;

import lombok.Setter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;


@Setter
public class RestParams {

    public static final Integer DEFAULT_OFFSET = 0;
    public static final Integer DEFAULT_LIMIT = 10;
    public static final Integer MAX_LIMIT = 100;

    private Integer offset;
    private Integer limit;
    private String sort;

    public RestParams() {
        this.offset = DEFAULT_OFFSET;
        this.limit = DEFAULT_LIMIT;
    }

    public RestParams(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public RestParams(int offset, int limit, String sort) {
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    public Pageable toPageable() {
        return new OffsetPageRequest(offset, limit, parseSort());
    }


    private Sort parseSort() {
        if (sort != null) {
            List<Sort.Order> orders = new ArrayList<>();

            String[] fields = sort.split(",");
            for (String field : fields) {
                String[] fieldParts = field.split(" ");
                if (fieldParts.length == 1) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, fieldParts[0], true, Sort.NullHandling.NATIVE));
                } else if (fieldParts.length == 2) {
                    orders.add(new Sort.Order(parseDirection(fieldParts[1]), fieldParts[0], true, Sort.NullHandling.NATIVE));
                }
            }

            if (orders.isEmpty()) {
                return Sort.unsorted();
            }

            return Sort.by(orders);
        }
        return Sort.unsorted();
    }

    private Sort.Direction parseDirection(String direction) {
        if ("DESC".equals(direction)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }
}

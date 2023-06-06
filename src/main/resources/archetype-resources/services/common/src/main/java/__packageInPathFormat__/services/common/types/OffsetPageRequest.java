package ${package}.services.common.types;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class OffsetPageRequest implements Pageable, Serializable {

    private final int limit;
    private final int offset;
    private final Sort sort;

    public OffsetPageRequest(int offset, int limit) {
        this(offset, limit, Sort.unsorted());
    }

    public OffsetPageRequest(int offset, int limit, Sort sort) {
        if (offset < 0) {
            offset = 0;
        }
        if (limit < 1) {
            limit = 1;
        }

        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetPageRequest((int) (getOffset() + getPageSize()), getPageSize(), getSort());
    }

    public OffsetPageRequest previous() {
        return hasPrevious() ? new OffsetPageRequest((int) (getOffset() - getPageSize()), getPageSize(), getSort()) : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetPageRequest(pageNumber * getPageSize(), getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}

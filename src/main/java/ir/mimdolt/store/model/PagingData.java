package ir.mimdolt.store.model;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */
public class PagingData<E> {
    private List<E> data;
    private int offset;
    private int count;

    public PagingData(List<E> data, int offset, int count) {
        this.data = data;
        this.offset = offset;
        this.count = count;
    }

    public PagingData() {
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

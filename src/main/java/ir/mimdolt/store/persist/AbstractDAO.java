package ir.mimdolt.store.persist;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/3/2016.
 */
public interface AbstractDAO<E, K> {
    public void add(E entity) throws Exception;

    public void saveOrUpdate(E entity)throws Exception;

    public void update(E entity)throws Exception;

    public void remove(E entity)throws Exception;

    public E find(K key)throws Exception;

    public List<E> getAll()throws Exception;

//    public PagingData<E> paging(Integer offset,Integer maxResult);
}

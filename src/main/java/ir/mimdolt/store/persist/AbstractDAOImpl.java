package ir.mimdolt.store.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/3/2016.
 */
@Repository
public abstract class AbstractDAOImpl<E, K extends Serializable> implements AbstractDAO<E, K> {

    @Autowired
    private SessionFactory sessionFactory;



    protected Class<? extends E> daoType;

    public AbstractDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) throws Exception {
        currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) throws Exception {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(E entity) throws Exception {
        currentSession().merge(entity);
    }

    @Override
    public void remove(E entity) throws Exception {
        currentSession().delete(entity);
    }

    @Override
    public E find(K key) throws Exception {
      E  t = (E) currentSession().get(daoType, key);
        return t;
    }

    @Override
    public List<E> getAll() throws Exception {
        return currentSession().createCriteria(daoType).list();
    }

}

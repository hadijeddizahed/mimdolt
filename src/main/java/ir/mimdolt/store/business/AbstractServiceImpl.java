package ir.mimdolt.store.business;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Hadi Jeddi Zahed on 11/3/2016.
 */
@Service
@Transactional
public abstract class AbstractServiceImpl<D, K, E> implements AbstractService<D, K, E> {

    @Override
    public String getEntityClassName() throws Exception {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return ((Class) pt.getActualTypeArguments()[2]).getSimpleName();
    }

}

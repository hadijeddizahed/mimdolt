package ir.mimdolt.store.persist.entity.user;

import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.AbstractDAO;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */
public interface UserDao extends AbstractDAO<User,Long> {

    User find(String username)throws Exception;

    PagingData<User> paging(Integer offset, Integer maxResult) throws Exception;
}

package ir.mimdolt.store.persist.entity.order;

import ir.mimdolt.store.persist.AbstractDAO;

/**
 * Created by Hadi Jeddi Zahed on 5/11/2017.
 */
public interface OrderDao extends AbstractDAO<Order, Long> {

    void save(String username, String session) throws Exception;
}

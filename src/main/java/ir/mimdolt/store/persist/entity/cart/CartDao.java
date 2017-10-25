package ir.mimdolt.store.persist.entity.cart;

import ir.mimdolt.store.persist.AbstractDAO;
import ir.mimdolt.store.persist.entity.user.User;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */
public interface CartDao extends AbstractDAO<Cart, Long> {

    List<Cart> listAll(User user) throws Exception;

    Cart find(User user)throws Exception;

    Cart findByCode(String code)throws Exception;

    void remove(User user, String code) throws Exception;

}

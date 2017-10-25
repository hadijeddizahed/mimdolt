package ir.mimdolt.store.persist.entity.cartitem;

import ir.mimdolt.store.persist.AbstractDAO;
import ir.mimdolt.store.persist.entity.user.User;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */
public interface CartItemDao extends AbstractDAO<CartItem, Long> {

    void remove(User user, String code, Long productId) throws Exception;

    CartItem findByCode(String code) throws Exception;
}

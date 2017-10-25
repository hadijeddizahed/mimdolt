package ir.mimdolt.store.persist.entity.order;

import ir.mimdolt.store.persist.AbstractDAOImpl;
import ir.mimdolt.store.persist.entity.cart.Cart;
import ir.mimdolt.store.persist.entity.cart.CartDao;
import ir.mimdolt.store.persist.entity.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Hadi Jeddi Zahed on 5/11/2017.
 */
@Repository
public class OrderDaoImpl extends AbstractDAOImpl<Order, Long> implements OrderDao {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void save(String username, String code) throws Exception {
        Cart cart = cartDao.findByCode(code);
        Cart userCart = cartDao.find(userDao.find(username));
    }
}

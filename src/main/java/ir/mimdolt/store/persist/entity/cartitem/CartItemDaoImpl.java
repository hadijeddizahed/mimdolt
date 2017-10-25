package ir.mimdolt.store.persist.entity.cartitem;

import ir.mimdolt.store.persist.AbstractDAOImpl;
import ir.mimdolt.store.persist.entity.cart.CartDao;
import ir.mimdolt.store.persist.entity.product.ProductDao;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.persist.entity.user.UserDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */
@Repository
public class CartItemDaoImpl extends AbstractDAOImpl<CartItem, Long> implements CartItemDao {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDao cartDao;

    @Override
    public void remove(User user, String code, Long productId) throws Exception {

    }

    @Override
    public CartItem findByCode(String code) throws Exception {
        Criteria criteria = currentSession().createCriteria(CartItem.class);
        criteria.add(Restrictions.eq("code", code));
        return (CartItem) criteria.uniqueResult();
    }
}

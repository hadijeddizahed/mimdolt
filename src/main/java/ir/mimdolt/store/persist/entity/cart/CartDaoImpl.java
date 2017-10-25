package ir.mimdolt.store.persist.entity.cart;

import ir.mimdolt.store.persist.AbstractDAOImpl;
import ir.mimdolt.store.persist.entity.user.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */
@Repository
public class CartDaoImpl extends AbstractDAOImpl<Cart, Long> implements CartDao {

    @Override
    public List<Cart> listAll(User user) throws Exception {
        Criteria criteria = currentSession().createCriteria(Cart.class, "cart");
        criteria.createAlias("cart.user", "user");
        criteria.add(Restrictions.eq("user", user));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Cart find(User user) throws Exception {
        Criteria criteria = currentSession().createCriteria(Cart.class, "cart");
        criteria.createAlias("cart.user", "user");
        criteria.add(Restrictions.eq("user", user));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list().size() != 0 ? (Cart) criteria.list().get(0) : null;
    }

    @Override
    public Cart findByCode(String code) throws Exception {
        Criteria criteria = currentSession().createCriteria(Cart.class, "cart");
        criteria.add(Restrictions.eq("code", code));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list().size() != 0 ? (Cart) criteria.list().get(0) : null;
    }

    @Override
    public void remove(User user, String code) throws Exception {

    }
}

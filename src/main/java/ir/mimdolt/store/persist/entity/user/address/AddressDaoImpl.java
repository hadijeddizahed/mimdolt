package ir.mimdolt.store.persist.entity.user.address;

import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.persist.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 5/5/2017.
 */

@Repository
public class AddressDaoImpl extends AbstractDAOImpl<Address, Long> implements AddressDao {

    @Autowired
    private UserService userService;

    @Override
    public List<Address> list() throws Exception {
        Criteria criteria = currentSession().createCriteria(Address.class, "address");
        criteria.createAlias("address.user", "user");
        criteria.add(Restrictions.eq("user", userService.getLoggedInUser()));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Address getDefaultAddress() throws Exception {
        Criteria criteria = currentSession().createCriteria(Address.class, "address");
        criteria.createAlias("address.user", "user");
        criteria.add(Restrictions.eq("user", userService.getLoggedInUser()));
        criteria.add(Restrictions.eq("defaultAddress", Boolean.TRUE));
        return (Address) criteria.uniqueResult();
    }
}

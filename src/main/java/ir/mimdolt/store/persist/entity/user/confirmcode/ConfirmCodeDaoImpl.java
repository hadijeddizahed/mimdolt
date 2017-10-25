package ir.mimdolt.store.persist.entity.user.confirmcode;

import ir.mimdolt.store.persist.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 5/4/2017.
 */

@Repository
public class ConfirmCodeDaoImpl extends AbstractDAOImpl<ConfirmCode,Long> implements ConfirmCodeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ConfirmCode find(String username) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConfirmCode.class);
        criteria.add(Restrictions.eq("username",username));
        return (ConfirmCode) criteria.uniqueResult();
    }

    @Override
    public List<ConfirmCode> list(String username) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConfirmCode.class);
        criteria.add(Restrictions.eq("username",username));
        criteria.add(Restrictions.eq("status", Boolean.TRUE));
        return criteria.list();
    }
}

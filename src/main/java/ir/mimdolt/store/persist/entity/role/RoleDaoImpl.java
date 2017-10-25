package ir.mimdolt.store.persist.entity.role;

import ir.mimdolt.store.persist.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */

@Repository
public class RoleDaoImpl extends AbstractDAOImpl<Role, Long> implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role find(String name) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
        criteria.add(Restrictions.eq("name", name));
        Role role = (Role) criteria.uniqueResult();
        return role;
    }
}

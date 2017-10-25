package ir.mimdolt.store.persist.entity.user;

import ir.mimdolt.store.model.PagingData;
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
public class UserDaoImpl extends AbstractDAOImpl<User, Long> implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public User find(String username) throws Exception {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        User user = (User)criteria.uniqueResult();
        return user;
    }

    @Override
    public PagingData<User> paging(Integer offset, Integer maxResult) throws Exception {
        PagingData<User> pagingData = new PagingData<>();
        Criteria criteria = currentSession().createCriteria(daoType);

        int count = (Integer) criteria.list().size() / maxResult;
        pagingData.setCount(count == 0 ? 1 : count);
        criteria.setFirstResult(offset * maxResult)
                .setMaxResults(maxResult != null ? maxResult : 10);
        pagingData.setData(criteria.list());
        return pagingData;
    }
}

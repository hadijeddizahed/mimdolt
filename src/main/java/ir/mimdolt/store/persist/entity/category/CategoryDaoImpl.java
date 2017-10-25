package ir.mimdolt.store.persist.entity.category;


import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.AbstractDAOImpl;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */
@Repository
public class CategoryDaoImpl extends AbstractDAOImpl<Category,Long> implements CategoryDao {
    @Override
    public PagingData<Category> paging(Integer offset, Integer maxResult) throws Exception {
        PagingData<Category> pagingData = new PagingData<>();
        Criteria criteria = currentSession().createCriteria(daoType);
        criteria.add(Restrictions.isNull("parent"));
        int count = (Integer) criteria.list().size() / maxResult;
        pagingData.setCount(count == 0 ? 1 : count);
        criteria.setFirstResult(offset * maxResult)
                .setMaxResults(maxResult != null ? maxResult : 10);
        pagingData.setData(criteria.list());
        return pagingData;
    }

    @Override
    public Category findByCode(String code) throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class, "category");
        criteria.add(Restrictions.eq("code", code));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list().size() != 0 ? (Category) criteria.list().get(0) : null;
    }

    @Override
    public List<Category> listParentNull() throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class);
        criteria.add(Restrictions.isNull("parent"));
        return criteria.list();
    }

    @Override
    public List<Category> listParentIsNotNull() throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class);
        criteria.add(Restrictions.isNotNull("parent"));
        return criteria.list();
    }

    @Override
    public List<Category> listCategory(Set<Long> ids) throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class);
        criteria.add(Restrictions.in("id",ids));
        return criteria.list();
    }

    @Override
    public List<Category> listAll() throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class);
        return criteria.list();
    }

    @Override
    public List<Category> listChild() throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class);
        criteria.add(Restrictions.isEmpty("childes"));
        return criteria.list();
    }

    @Override
    public List<Category> listByQuery(String query) throws Exception {
        Criteria criteria = currentSession().createCriteria(Category.class);
        criteria.add(Restrictions.like("name",query));
        return criteria.list();
    }
}

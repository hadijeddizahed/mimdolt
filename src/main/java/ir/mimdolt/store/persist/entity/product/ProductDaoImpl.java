package ir.mimdolt.store.persist.entity.product;

import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.AbstractDAOImpl;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValueDao;
import ir.mimdolt.store.persist.entity.product.attribute.Attribute;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
@Repository
public class ProductDaoImpl extends AbstractDAOImpl<Product, Long> implements ProductDao {

    @Autowired
    private OptionValueDao optionValueDao;


    @Override
    public PagingData<Product> paging(Integer offset, Integer maxResult, List<Long> optionValue, String code) throws Exception {
        List<Attribute> attributes = new ArrayList<>();
        List<Long> longList = new ArrayList<>();
        Criteria aCriteria = null;
        if (optionValue != null && !optionValue.isEmpty()) {
            aCriteria = currentSession().createCriteria(Attribute.class, "attribute");
            aCriteria.createAlias("attribute.optionValue", "optionValue");
            aCriteria.add(Restrictions.in("optionValue.id", optionValue));
            attributes = aCriteria.list();
        }
        PagingData<Product> pagingData = new PagingData<>();
        Criteria criteria = currentSession().createCriteria(daoType, "product");

        if (code != null) {
            criteria.createAlias("product.categorySet", "category");
            criteria.add(Restrictions.eq("category.code", code));
        }
        if (attributes != null && !attributes.isEmpty()) {
            for (Attribute attribute:attributes){
                longList.add(attribute.getId());
            }
            criteria.createAlias("product.attributeSet", "attribute");
            criteria.add(Restrictions.in("attribute.id", longList));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        }

        int count = (Integer) criteria.list().size() / maxResult;
        pagingData.setCount(count == 0 ? 1 : count);
        criteria.setFirstResult(offset * maxResult)
                .setMaxResults(maxResult != null ? maxResult : 10);
        pagingData.setData(criteria.list());
        return pagingData;
    }

    @Override
    public Product findByCode(String code) throws Exception {
        Criteria criteria = currentSession().createCriteria(Product.class, "product");
        criteria.add(Restrictions.eq("code", code));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list().size() != 0 ? (Product) criteria.list().get(0) : null;
    }

    @Override
    public List<Product> getLastSeen() throws Exception {
        Criteria criteria = currentSession().createCriteria(Product.class, "product");
        criteria.addOrder(Order.desc("lastSeen"));
        criteria.setFirstResult(1);
        criteria.setMaxResults(10);
        return criteria.list();
    }
}

package ir.mimdolt.store.persist.entity.product.attribute;

import ir.mimdolt.store.persist.AbstractDAOImpl;
import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.persist.entity.category.CategoryDao;
import ir.mimdolt.store.persist.entity.product.Product;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */
@Repository
public class AttributeDaoImpl extends AbstractDAOImpl<Attribute, Long> implements AttributeDao {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Attribute> findByCategory(String code) throws Exception {
        Category category = categoryDao.findByCode(code);
        List<Product> productList = category.getProducts();
        if(!productList.isEmpty()) {
            Criteria criteria = currentSession().createCriteria(Attribute.class, "attribute");
            criteria.createAlias("attribute.product", "product");
            criteria.add(Restrictions.in("product", productList));
            criteria.add(Restrictions.or(Property.forName("viewType").eq(AttributeViewType.FILTER_PANEL.getValue()),
                    Property.forName("viewType").eq(AttributeViewType.BOTH.getValue())));
            return criteria.list();
        }
        return new ArrayList<>();
    }
}

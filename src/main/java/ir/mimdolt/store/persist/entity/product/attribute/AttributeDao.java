package ir.mimdolt.store.persist.entity.product.attribute;

import ir.mimdolt.store.persist.AbstractDAO;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */
public interface AttributeDao extends AbstractDAO<Attribute, Long> {

    List<Attribute> findByCategory(String code) throws Exception;
}

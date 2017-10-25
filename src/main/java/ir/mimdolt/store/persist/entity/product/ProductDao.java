package ir.mimdolt.store.persist.entity.product;

import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.AbstractDAO;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
public interface ProductDao extends AbstractDAO<Product, Long> {

    PagingData<Product> paging(Integer offset, Integer maxResult,List<Long> option,String code) throws Exception;

    Product findByCode(String code) throws Exception;

    List<Product> getLastSeen() throws Exception;
}

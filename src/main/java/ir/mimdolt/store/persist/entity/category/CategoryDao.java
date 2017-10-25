package ir.mimdolt.store.persist.entity.category;


import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.AbstractDAO;

import java.util.List;
import java.util.Set;


/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */
public interface CategoryDao extends AbstractDAO<Category, Long> {

    PagingData<Category> paging(Integer offset, Integer maxResult) throws Exception;

    Category findByCode(String code) throws Exception;

    List<Category> listParentNull()throws Exception;

    List<Category> listParentIsNotNull()throws Exception;

    List<Category> listCategory(Set<Long> ids) throws Exception;

    List<Category> listAll() throws Exception;

    List<Category> listChild() throws Exception;

    List<Category> listByQuery(String query) throws Exception;
}

package ir.mimdolt.store.business.category;


import ir.mimdolt.store.business.AbstractService;

import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.web.dto.category.CategoryDTO;
import ir.mimdolt.store.web.dto.category.FlatCategoryDto;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */

public interface CategoryService extends AbstractService<CategoryDTO, Long, Category> {

    CategoryDTO find(Long id) throws Exception;

    CategoryDTO saveOrUpdate(CategoryDTO categoryDto) throws Exception;

    CategoryDTO update(CategoryDTO categoryDTO) throws Exception;

    List<CategoryDTO> listAll()throws Exception;

    List<CategoryDTO> listAllChilde() throws Exception;

    List<FlatCategoryDto> listAllAsFlat()throws Exception;

    List<FlatCategoryDto> listByQuery(String query) throws Exception;

    List<FlatCategoryDto> listChildes() throws Exception;

    PagingData<CategoryDTO> paging(Integer offset, Integer maxResult)throws Exception;

    byte[]  displayImage(String url, String size, String name) throws Exception;

}

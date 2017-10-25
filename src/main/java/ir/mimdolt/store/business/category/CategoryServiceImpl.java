package ir.mimdolt.store.business.category;


import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.persist.entity.category.CategoryDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.category.CategoryDTO;
import ir.mimdolt.store.web.dto.category.FlatCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractServiceImpl<CategoryDTO, Long, Category> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    private final static  String BASE_PATH = "/tmp/category/";

    @Override
    public CategoryDTO find(Long id) throws Exception {
        Category category = categoryDao.find(id);
        if (category == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.map(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO saveOrUpdate(CategoryDTO categoryDto) throws Exception {
        Category category = null;
        Category parent = null;
        if (categoryDto.getId() == null)
            category = new Category();
        else
            category = categoryDao.find(categoryDto.getId());
        if (categoryDto.getParentId() != null)
            parent = categoryDao.find(categoryDto.getParentId().longValue());
        category.map(categoryDto, parent);
        categoryDao.update(category);
        return categoryDto;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) throws Exception {
        Category category = new Category();
        category.map(categoryDTO, null);
        categoryDao.update(category);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> listAll() throws Exception {
        List<Category> categories = categoryDao.listParentNull();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for(Category category:categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.map(category);
            categoryDTOs.add(categoryDTO);
        }

        return categoryDTOs;
    }

    @Override
    public List<CategoryDTO> listAllChilde() throws Exception {
        List<Category> categories = categoryDao.listParentIsNotNull();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for(Category category:categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.map(category);
            categoryDTOs.add(categoryDTO);
        }

        return categoryDTOs;
    }

    @Override
    public List<FlatCategoryDto> listAllAsFlat() throws Exception {
        List<Category> categories = categoryDao.listAll();
        List<FlatCategoryDto> flatCategoryDtos = new ArrayList<>();
        for(Category category:categories){
            FlatCategoryDto flatCategoryDto = new FlatCategoryDto();
            flatCategoryDto.setId(category.getId());
            String name = category.getName();
            Category p = category.getParent();
             while (p!= null){
              name += "|"+p.getName();
              p = p.getParent();
             }
            flatCategoryDto.setName(name);
            flatCategoryDtos.add(flatCategoryDto);
        }
        flatCategoryDtos.sort(Comparator.comparing(FlatCategoryDto::getName));
        return flatCategoryDtos;
    }


    @Override
    public List<FlatCategoryDto> listByQuery(String query) throws Exception {
        List<Category> categories = categoryDao.listByQuery(query);
        List<FlatCategoryDto> flatCategoryDtos = new ArrayList<>();
        for(Category category:categories){
            FlatCategoryDto flatCategoryDto = new FlatCategoryDto();
            flatCategoryDto.setId(category.getId());
            String name = category.getName();
            flatCategoryDto.setName(name);
            flatCategoryDtos.add(flatCategoryDto);
        }
        flatCategoryDtos.sort(Comparator.comparing(FlatCategoryDto::getName));
        return flatCategoryDtos;
    }

    @Override
    public List<FlatCategoryDto> listChildes() throws Exception {
        List<Category> categories = categoryDao.listChild();
        List<FlatCategoryDto> flatCategoryDtos = new ArrayList<>();
        for(Category category:categories){
            FlatCategoryDto flatCategoryDto = new FlatCategoryDto();
            flatCategoryDto.setId(category.getId());
            String name = category.getName();
            flatCategoryDto.setName(name);
            flatCategoryDtos.add(flatCategoryDto);
        }
        flatCategoryDtos.sort(Comparator.comparing(FlatCategoryDto::getName));
        return flatCategoryDtos;
    }

    @Override
    public PagingData<CategoryDTO> paging(Integer offset, Integer maxResult) throws Exception {
        PagingData<Category> categoryPagingData = categoryDao.paging(offset, maxResult);
        PagingData<CategoryDTO> categoryFindDTOPagingData = new PagingData<CategoryDTO>();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryPagingData.getData()) {
            if (category.getParent() == null)
                categoryDTOList.add(new CategoryDTO().map(category));
        }
        categoryFindDTOPagingData.setData(categoryDTOList);
        categoryFindDTOPagingData.setCount(categoryPagingData.getCount());
        return categoryFindDTOPagingData;
    }

    @Override
    public byte[] displayImage(String code, String size, String name) throws Exception {
        String path = BASE_PATH + code + File.separator + size + File.separator + name;
        Path path1 = Paths.get(path);
        return Files.readAllBytes(path1);
    }
}

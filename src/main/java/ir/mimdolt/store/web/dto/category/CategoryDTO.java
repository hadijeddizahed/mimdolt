package ir.mimdolt.store.web.dto.category;

import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.web.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */
public class CategoryDTO extends BaseDTO {


    private Long id;
    @NotNull(message = "error.nutnull")
    @Size(min = 3, message = "error.size")
    private String name;
    private String value;
    private String code;
    private String imageName;
    private int viewOrder;
    private Long parentId;
    private String description;
    private String parentName;
    private Set<CategoryDTO> children = new HashSet<>();

    public CategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getViewOrder() {
        return viewOrder;
    }

    public void setViewOrder(int viewOrder) {
        this.viewOrder = viewOrder;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Set<CategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(Set<CategoryDTO> children) {
        this.children = children;
    }

    public CategoryDTO map(Category category) throws InvocationTargetException, IllegalAccessException {
        if (category == null)
            return new CategoryDTO();

        this.setName(category.getName());
        this.setValue(category.getValue());
        this.setImageName(category.getImageName());
        this.setDescription(category.getDescription());
        this.setCode(category.getCode());
        this.setId(category.getId());
        this.setViewOrder(category.getViewOrder());
        this.setEnable(category.isEnable());
        if (category.getParent() != null) {
            this.setParentName(category.getParent().getName());
            this.setParentId(category.getParent().getId());
        }

        if (category.getChildes().size() != 0) {
            for (Category category1 : category.getChildes()) {
                this.getChildren().add(simpleMap(category1));
            }
        }
        return this;
    }

    private CategoryDTO simpleMap(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        if (category != null) {
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setId(category.getId());
            categoryDTO.setCode(category.getCode());
            categoryDTO.setValue(category.getValue());
            categoryDTO.setEnable(category.isEnable());
            if (category.getParent() != null) {
                categoryDTO.setParentName(category.getParent().getName());
                categoryDTO.setParentId(category.getParent().getId());
            }
            if (category.getChildes().size() != 0) {
                for (Category category1 : category.getChildes()) {
                    categoryDTO.getChildren().add(simpleMap(category1));
                }
            }

        }
        return categoryDTO;
    }
}

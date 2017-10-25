package ir.mimdolt.store.web.dto.category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Hadi Jeddi Zahed on 3/1/2017.
 */
public class CategoryBaseDto {


    private Long id;
    @NotNull(message = "error.nutnull")
    @Size(min = 3, message = "error.size")
    private String name;
    private String value;
    private String imageName;
    private Long parentId;
    private String description;
    private boolean enable = true;

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public CategoryDTO map(){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(this.getId());
        categoryDTO.setName(this.getName());
        categoryDTO.setDescription(this.getDescription());
        categoryDTO.setValue(this.getValue());
        categoryDTO.setImageName(this.getImageName());
        categoryDTO.setParentId(this.getParentId());
        categoryDTO.setEnable(this.isEnable());
        return categoryDTO;
    }
}

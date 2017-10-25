package ir.mimdolt.store.web.dto.option;

/**
 * Created by Hadi Jeddi Zahed on 2/10/2017.
 */
public class OptionValueDto {

    private Long id;
    private String value;
    private String classValue;
    private Integer sortOrder;
    private String description;

    public OptionValueDto() {
    }

    public OptionValueDto(String value, Integer sortOrder, String description) {
        this.value = value;
        this.sortOrder = sortOrder;
        this.description = description;
    }

    public OptionValueDto(String value, String classValue, Integer sortOrder, String description) {
        this.value = value;
        this.classValue = classValue;
        this.sortOrder = sortOrder;
        this.description = description;
    }

    public OptionValueDto(Long id, String value,String classValue, Integer sortOrder, String description) {
        this.id = id;
        this.value = value;
        this.classValue = classValue;
        this.sortOrder = sortOrder;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

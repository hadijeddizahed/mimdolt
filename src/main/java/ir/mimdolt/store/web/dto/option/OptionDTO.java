package ir.mimdolt.store.web.dto.option;

import ir.mimdolt.store.web.dto.BaseDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */
public class OptionDTO extends BaseDTO {

    private Long id;

    private String name;

    private String type;

    private Integer sortOrder;

    private Set<OptionValueDto> values = new HashSet<>();

    public OptionDTO() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Set<OptionValueDto> getValues() {
        return values;
    }

    public void setValues(Set<OptionValueDto> values) {
        this.values = values;
    }
}

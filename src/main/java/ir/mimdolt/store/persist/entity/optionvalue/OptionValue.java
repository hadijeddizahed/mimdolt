package ir.mimdolt.store.persist.entity.optionvalue;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.option.Option;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */

@Entity
@Table(name = "option_value", schema = "", catalog = "mimdolt")
public class OptionValue extends BaseEntity implements Serializable {


    private static final long serialVersionUID = 4261808713095764586L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value",updatable = true)
    private String value;

    @Column(name = "class_value",updatable = true)
    private String classValue;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "option_id")
    @JsonBackReference
    private Option option;


    public OptionValue(String value, Integer sortOrder, String description) {
        this.value = value;
        this.sortOrder = sortOrder;
        this.description = description;
    }

    public OptionValue(String value, String classValue, Integer sortOrder, String description) {
        this.value = value;
        this.classValue = classValue;
        this.sortOrder = sortOrder;
        this.description = description;
    }

    public OptionValue(Long id,String value, Integer sortOrder, String description) {
        this.id = id;
        this.value = value;
        this.sortOrder = sortOrder;
        this.description = description;
    }

    public OptionValue(String value) {
        this.value = value;
    }

    public OptionValue() {

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

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

}

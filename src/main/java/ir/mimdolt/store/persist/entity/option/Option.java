package ir.mimdolt.store.persist.entity.option;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */

@Entity
@Table(name = "option", schema = "", catalog = "mimdolt")
public class Option extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -8605817015286795180L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "option_type")
    private String optionType;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "option")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<OptionValue> optionValueSet;


    public Option() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<OptionValue> getOptionValueSet() {
        return optionValueSet;
    }

    public void setOptionValueSet(Set<OptionValue> optionValueSet) {
        this.optionValueSet = optionValueSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (id != null ? !id.equals(option.id) : option.id != null) return false;
        if (name != null ? !name.equals(option.name) : option.name != null) return false;
        return !(optionType != null ? !optionType.equals(option.optionType) : option.optionType != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (optionType != null ? optionType.hashCode() : 0);
        return result;
    }
}

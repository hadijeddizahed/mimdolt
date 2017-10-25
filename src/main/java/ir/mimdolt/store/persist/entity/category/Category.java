package ir.mimdolt.store.persist.entity.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.web.dto.category.CategoryDTO;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */

@Entity
@Table(name = "category")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -68765266043129222L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "view_order",columnDefinition = "int default 0")
    private int viewOrder = 0;

    @OneToMany(mappedBy = "parent")
    @JsonBackReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("name ASC")
    private Set<Category> childes;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonManagedReference
    private Category parent;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorySet")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;


    public Category() {
    }

    public Category(Long id, String name, String description, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
//        this.parent = parent;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Category> getChildes() {
        return childes == null ? new HashSet<Category>() : new HashSet<>(childes);
    }

    public void setChildes(Set<Category> childes) {
        this.childes = childes;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category map(CategoryDTO categoryDTO, Category parent) {
        if (categoryDTO == null)
            return new Category();
        this.setId(categoryDTO.getId());
        this.setName(categoryDTO.getName());
        this.setValue(categoryDTO.getValue());
        this.setCode(categoryDTO.getCode());
        this.setEnable(categoryDTO.isEnable());
        this.setImageName(categoryDTO.getImageName());
        this.setViewOrder(categoryDTO.getViewOrder());
        this.setDescription(categoryDTO.getDescription());
        this.setParent(parent);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        return !(name != null ? !name.equals(category.name) : category.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
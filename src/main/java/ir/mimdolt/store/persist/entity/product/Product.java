package ir.mimdolt.store.persist.entity.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.cartitem.CartItem;
import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.persist.entity.product.attribute.Attribute;
import ir.mimdolt.store.persist.entity.product.attribute.AttributeViewType;
import ir.mimdolt.store.persist.entity.product.manufacturer.Manufacturer;
import ir.mimdolt.store.persist.entity.product.relationship.ProductRelationship;
import ir.mimdolt.store.persist.entity.productimage.ProductImage;
import ir.mimdolt.store.web.dto.product.ProductDto;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Hadi Jeddi Zahed on 11/29/2016.
 */

@Entity
@Table(name = "product", schema = "", catalog = "mimdolt")
public class Product extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -8125570310708108230L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "title")
    private String title;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "price")
    private Double price;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_category", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id",
                    nullable = false)})
    @JsonBackReference
    private Set<Category> categorySet = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private Set<ProductRelationship> relationships = new HashSet<ProductRelationship>();

    @Column(name = "short_description", length = 300)
    private String shortDescription;

    @Column(name = "full_description", length = 1000)
    private String fullDescription;

    @Column(name = "last_seen")
    private Date lastSeen;

    @OneToOne
    @JoinColumn(name = "MANUFACTURER_ID")
    private Manufacturer manufacturer;

    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private ProductImage defaultImage;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<ProductImage> productImageList;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<Attribute> attributeSet = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private Set<CartItem> cartItemSet;


    public Product() {
    }

    public Product(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductImage getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ProductImage defaultImage) {
        this.defaultImage = defaultImage;
    }

    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    public Set<Attribute> getAttributeSet() {
        return attributeSet.stream().filter(attribute -> (attribute.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue() ||
                attribute.getViewType() == AttributeViewType.BOTH.getValue())).collect(Collectors.toSet());
    }

    public void setAttributeSet(Set<Attribute> attributeSet) {
        this.attributeSet = attributeSet;
    }

    public Set<CartItem> getCartItemSet() {
        return cartItemSet;
    }

    public void setCartItemSet(Set<CartItem> cartItemSet) {
        this.cartItemSet = cartItemSet;
    }

    public Set<ProductRelationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(Set<ProductRelationship> relationships) {
        this.relationships = relationships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        return !(categorySet != null ? !categorySet.equals(product.categorySet) : product.categorySet != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (categorySet != null ? categorySet.hashCode() : 0);
        return result;
    }

    public Product map(ProductDto productDto, Set<Category> categorySet) throws InvocationTargetException, IllegalAccessException {
        if (productDto == null)
            return new Product();
        this.setId(productDto.getId());
        this.setTitle(productDto.getTitle());
        this.setCode(UUID.randomUUID().toString());
        this.setPrice(productDto.getPrice());
        this.setShortDescription(productDto.getShortDescription());
        this.setFullDescription(productDto.getFullDescription());
        this.setEnable(productDto.isEnable());
        for (Category category : this.getCategorySet()) {
            if (!categorySet.contains(category)) {
                this.getCategorySet().remove(category);
            }
        }
        this.getCategorySet().addAll(categorySet);
        return this;
    }
}

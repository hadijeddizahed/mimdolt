package ir.mimdolt.store.web.dto.product;

import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.web.dto.BaseDTO;
import ir.mimdolt.store.web.dto.product.attribute.AttributeDisplay;
import ir.mimdolt.store.web.dto.product.attribute.AttributeDto;
import ir.mimdolt.store.web.dto.product.category.ProductCategory;
import ir.mimdolt.store.web.dto.product.manufacturer.ManufacturerDto;
import ir.mimdolt.store.web.dto.product.relationship.ProductRelationshipDto;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;

import java.util.*;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
public class ProductDto extends BaseDTO {

    private Long id;
    private String title;
    private String code;
    private Double price;
    private Set<ProductCategory> categories = new HashSet<>();
    private String shortDescription;
    private String fullDescription;
    private ManufacturerDto manufacturer;
    private List<AttributeDto> attributes;
    private HashMap<String, AttributeDisplay> attributesMap = new HashMap<>();
    private ProductImageDto defaultImage = new ProductImageDto();
    private List<ProductImageDto> images = new ArrayList<>();
    private List<ProductRelationshipDto> relationshipProducts = new ArrayList<>();


    public ProductDto() {
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

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategory> categories) {
        this.categories = categories;
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

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<AttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeDto> attributes) {
        this.attributes = attributes;
    }

    public HashMap<String, AttributeDisplay> getAttributesMap() {
        return attributesMap;
    }

    public void setAttributesMap(HashMap<String, AttributeDisplay> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public ProductImageDto getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ProductImageDto defaultImage) {
        this.defaultImage = defaultImage;
    }

    public List<ProductImageDto> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDto> images) {
        this.images = images;
    }

    public List<ProductRelationshipDto> getRelationshipProducts() {
        return relationshipProducts;
    }

    public void setRelationshipProducts(List<ProductRelationshipDto> relationshipProducts) {
        this.relationshipProducts = relationshipProducts;
    }

    public ProductDto map(Product product) throws Exception {
        if (product == null)
            return new ProductDto();
        this.setId(product.getId());
        this.setTitle(product.getTitle());
        this.setCode(product.getCode());
        this.setPrice(product.getPrice());
        this.setShortDescription(product.getShortDescription());
        this.setFullDescription(product.getFullDescription());
        if (product.getDefaultImage() != null)
            this.setDefaultImage(new ProductImageDto().map(product.getDefaultImage()));
        for (Category category : product.getCategorySet()) {
            this.getCategories().add(new ProductCategory(category.getId(),category.getName()));
        }

        this.setEnable(product.isEnable());
        return this;
    }
}

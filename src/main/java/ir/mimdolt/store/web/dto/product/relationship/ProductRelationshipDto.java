package ir.mimdolt.store.web.dto.product.relationship;

import ir.mimdolt.store.web.dto.productimage.ProductImageDto;

/**
 * Created by Hadi Jeddi Zahed on 3/21/2017.
 */
public class ProductRelationshipDto {

    private Long id;
    private String title;
    private String code;
    private Double price;
    private ProductImageDto defaultImage = new ProductImageDto();

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

    public ProductImageDto getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ProductImageDto defaultImage) {
        this.defaultImage = defaultImage;
    }
}

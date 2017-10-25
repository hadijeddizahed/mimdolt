package ir.mimdolt.store.persist.entity.productimage;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 11/29/2016.
 */

@Entity
@Table(name = "product_image",schema = "",catalog = "mimdolt")
public class ProductImage extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 4456289196096788490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String thumbnailUrl;

    @Column(name = "size_")
    private Long size;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "code")
    private String code;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName="id")
    @JsonManagedReference
    private Product product;

    @OneToOne(mappedBy = "defaultImage")
    @PrimaryKeyJoinColumn
    private Product ownerProduct;

    public ProductImage() {
    }

    public ProductImage(String thumbnailUrl, String url, Product product) {
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getOwnerProduct() {
        return ownerProduct;
    }

    public void setOwnerProduct(Product ownerProduct) {
        this.ownerProduct = ownerProduct;
    }

    public ProductImage map(ProductImageDto productImageDto, Product product) {
        if (productImageDto == null)
            return new ProductImage();
//        this.setId(productImageDto.getId());

        this.setUrl(productImageDto.getUrl());
        this.setCode(productImageDto.getCode());
        this.setProduct(product);
        this.setName(productImageDto.getImageName());
        return this;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductImage that = (ProductImage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(product != null ? !product.equals(that.product) : that.product != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}

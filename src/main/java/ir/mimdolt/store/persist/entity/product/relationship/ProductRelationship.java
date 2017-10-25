package ir.mimdolt.store.persist.entity.product.relationship;

import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.product.Product;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 3/21/2017.
 */

@Entity
@Table(name = "product_relationship", schema = "", catalog = "mimdolt")
public class ProductRelationship extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -8125570310708108130L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="PRODUCT_ID",updatable=false,nullable=true)
    private Product product = null;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="RELATED_PRODUCT_ID",updatable=false,nullable=true)
    private Product relatedProduct = null;

    @Column(name="CODE")
    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(Product relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRelationship)) return false;

        ProductRelationship that = (ProductRelationship) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getProduct() != null ? !getProduct().equals(that.getProduct()) : that.getProduct() != null) return false;
        if (getRelatedProduct() != null ? !getRelatedProduct().equals(that.getRelatedProduct()) : that.getRelatedProduct() != null)
            return false;
        return !(getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        result = 31 * result + (getRelatedProduct() != null ? getRelatedProduct().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        return result;
    }
}

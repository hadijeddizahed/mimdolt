package ir.mimdolt.store.persist.entity.producttype;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import ir.mimdolt.store.persist.entity.product.Product;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 12/29/2016.
 */


@Entity
@Table(name = "product_type", schema = "", catalog = "mimdolt")
public class ProductType extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -8125570310708108230L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private OptionValue optionValue;

    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName="id")
    @JsonManagedReference
    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public OptionValue getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(OptionValue optionValue) {
        this.optionValue = optionValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}

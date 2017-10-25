package ir.mimdolt.store.persist.entity.product.attribute;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.option.Option;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import ir.mimdolt.store.persist.entity.product.Product;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */

@Entity
@Table(name = "ATTRIBUTE", schema = "", catalog = "mimdolt")
public class Attribute extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "OPTION_ID")
    private Option option;

    @OneToOne
    @JoinColumn(name = "OPTION_VALUE_ID")
    private OptionValue optionValue;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID",referencedColumnName="id")
    @JsonManagedReference
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "view_type")
    private int viewType;

    public Attribute() {
    }

    public Attribute(Integer quantity, Double price, Double weight) {
        this.quantity = quantity;
        this.price = price;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attribute)) return false;

        Attribute attribute = (Attribute) o;

        if (getId() != null ? !getId().equals(attribute.getId()) : attribute.getId() != null) return false;
        if (getOption() != null ? !getOption().equals(attribute.getOption()) : attribute.getOption() != null)
            return false;
        if (getOptionValue() != null ? !getOptionValue().equals(attribute.getOptionValue()) : attribute.getOptionValue() != null)
            return false;
        return !(getProduct() != null ? !getProduct().equals(attribute.getProduct()) : attribute.getProduct() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getOption() != null ? getOption().hashCode() : 0);
        result = 31 * result + (getOptionValue() != null ? getOptionValue().hashCode() : 0);
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        return result;
    }
}

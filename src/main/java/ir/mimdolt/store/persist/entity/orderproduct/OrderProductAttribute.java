package ir.mimdolt.store.persist.entity.orderproduct;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Hadi Jeddi Zahed on 2/7/2017.
 */

@Entity
@Table (name="ORDER_PRODUCT_ATTRIBUTE" , schema="mimdolt")
public class OrderProductAttribute {

    @Id
    @Column(name="ORDER_PRODUCT_ATTRIBUTE_ID", nullable=false , unique=true )
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column ( name= "PRODUCT_ATTRIBUTE_PRICE" , nullable=false , precision=15 , scale=4 )
    private BigDecimal productAttributePrice;

    @Column ( name= "PRODUCT_ATTRIBUTE_IS_FREE" , nullable=false )
    private boolean productAttributeIsFree;

    @Column ( name= "PRODUCT_ATTRIBUTE_WEIGHT" , precision=15 , scale=4 )
    private java.math.BigDecimal productAttributeWeight;

    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID", nullable = false)
    private OrderProduct orderProduct;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getProductAttributePrice() {
        return productAttributePrice;
    }

    public void setProductAttributePrice(BigDecimal productAttributePrice) {
        this.productAttributePrice = productAttributePrice;
    }

    public boolean isProductAttributeIsFree() {
        return productAttributeIsFree;
    }

    public void setProductAttributeIsFree(boolean productAttributeIsFree) {
        this.productAttributeIsFree = productAttributeIsFree;
    }

    public BigDecimal getProductAttributeWeight() {
        return productAttributeWeight;
    }

    public void setProductAttributeWeight(BigDecimal productAttributeWeight) {
        this.productAttributeWeight = productAttributeWeight;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

}

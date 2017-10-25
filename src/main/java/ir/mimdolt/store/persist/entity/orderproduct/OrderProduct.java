package ir.mimdolt.store.persist.entity.orderproduct;

import ir.mimdolt.store.persist.entity.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 2/7/2017.
 */

@Entity
@Table(name = "ORDER_PRODUCT", schema = "mimdolt")
public class OrderProduct {

    @Id
    @Column(name = "ORDER_PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 64, nullable = false)
    private String productName;

    @Column(name = "PRODUCT_QUANTITY")
    private int productQuantity;

    @Column(name = "ONETIME_CHARGE", nullable = false)
    private BigDecimal oneTimeCharge;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;


    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductAttribute> orderAttributes = new HashSet<OrderProductAttribute>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getOneTimeCharge() {
        return oneTimeCharge;
    }

    public void setOneTimeCharge(BigDecimal oneTimeCharge) {
        this.oneTimeCharge = oneTimeCharge;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<OrderProductAttribute> getOrderAttributes() {
        return orderAttributes;
    }

    public void setOrderAttributes(Set<OrderProductAttribute> orderAttributes) {
        this.orderAttributes = orderAttributes;
    }
}

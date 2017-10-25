package ir.mimdolt.store.persist.entity.order;

import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.orderproduct.OrderProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 2/6/2017.
 */

@Entity
@Table (name="ORDERS" , schema = "", catalog = "mimdolt")
public class Order extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 3263009385625236106L;


    @Id
    @Column(name ="ORDER_ID" , unique=true , nullable=false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name ="ORDER_STATUS")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name ="LAST_MODIFIED")
    private Date lastModified;

    @Column (name ="CUSTOMER_ID")
    private Long customerId;

    @Temporal(TemporalType.DATE)
    @Column (name ="DATE_PURCHASED")
    private Date datePurchased;


    @Column (name ="ORDER_TOTAL")
    private BigDecimal total;



    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new LinkedHashSet<OrderProduct>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
        this.datePurchased = datePurchased;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

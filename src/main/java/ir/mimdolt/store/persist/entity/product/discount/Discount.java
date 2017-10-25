package ir.mimdolt.store.persist.entity.product.discount;

import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.common.model.audit.AuditListener;
import ir.mimdolt.store.persist.entity.common.model.audit.AuditSection;
import ir.mimdolt.store.persist.entity.common.model.audit.Auditable;
import ir.mimdolt.store.persist.entity.user.usergruop.UserGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "DISCOUNT", schema = "mimdolt")
public class Discount extends BaseEntity implements Serializable, Auditable {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "ORDER_AMOUNT", nullable = false)
    private Integer orderAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "PRICE")
    private Double price;

    @OneToOne
    @JoinColumn(name = "USER_GROUP_ID")
    private UserGroup userGroup;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public AuditSection getAuditSection() {
        return null;
    }

    @Override
    public void setAuditSection(AuditSection audit) {

    }
}

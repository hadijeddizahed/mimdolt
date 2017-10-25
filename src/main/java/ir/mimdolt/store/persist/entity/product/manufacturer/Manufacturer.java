package ir.mimdolt.store.persist.entity.product.manufacturer;

import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.common.model.audit.AuditListener;
import ir.mimdolt.store.persist.entity.common.model.audit.AuditSection;
import ir.mimdolt.store.persist.entity.common.model.audit.Auditable;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 2/13/2017.
 */

@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "MANUFACTURER", schema="mimdolt")
public class Manufacturer extends BaseEntity implements Serializable, Auditable {
    private static final long serialVersionUID = 80693964563570099L;

    @Id
    @Column(name = "MANUFACTURER_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotEmpty
    @Column(name="NAME", nullable = false, length=120)
    private String name;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name = "MANUFACTURER_IMAGE")
    private String image;

    @Column(name = "SORT_ORDER")
    private Integer order = new Integer(0);


    @NotEmpty
    @Column(name = "CODE", length = 100, nullable = false)
    private String code;

    public Manufacturer() {
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
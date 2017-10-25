package ir.mimdolt.store.persist.entity.role;

import ir.mimdolt.store.persist.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */

@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4575348465760592835L;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

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


}

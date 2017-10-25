package ir.mimdolt.store.persist.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */
@MappedSuperclass()
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 5156229556643135589L;

    @Column(name = "[enable]")
    private boolean enable = true;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


}

package ir.mimdolt.store.persist.entity.user.confirmcode;

import ir.mimdolt.store.persist.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 5/4/2017.
 */

@Entity
@Table(name = "confirm_code")
public class ConfirmCode extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1043183946099489566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CODE")
    private String code;

    @Column(name = "STATUS")
    private Boolean status;

    public ConfirmCode() {
    }

    public ConfirmCode(String username, String code, Boolean status) {
        this.username = username;
        this.code = code;
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfirmCode)) return false;

        ConfirmCode that = (ConfirmCode) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        return !(getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        return result;
    }
}

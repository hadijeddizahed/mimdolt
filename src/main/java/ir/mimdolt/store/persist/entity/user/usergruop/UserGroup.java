package ir.mimdolt.store.persist.entity.user.usergruop;

import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */

@Entity
@Table(name = "USER_GROUP", schema = "mimdolt")
public class UserGroup extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1043183916099489566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @OneToMany(mappedBy = "userGroup")
    private Set<User> userSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroup)) return false;

        UserGroup userGroup = (UserGroup) o;

        if (getId() != null ? !getId().equals(userGroup.getId()) : userGroup.getId() != null) return false;
        if (getGroupName() != null ? !getGroupName().equals(userGroup.getGroupName()) : userGroup.getGroupName() != null)
            return false;
        return !(getUserSet() != null ? !getUserSet().equals(userGroup.getUserSet()) : userGroup.getUserSet() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getGroupName() != null ? getGroupName().hashCode() : 0);
        result = 31 * result + (getUserSet() != null ? getUserSet().hashCode() : 0);
        return result;
    }
}

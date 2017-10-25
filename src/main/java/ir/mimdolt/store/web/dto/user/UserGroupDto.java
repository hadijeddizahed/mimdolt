package ir.mimdolt.store.web.dto.user;

/**
 * Created by Hadi Jeddi Zahed on 2/24/2017.
 */
public class UserGroupDto {

    private Long id;
    private String GroupName;

    public UserGroupDto() {
    }

    public UserGroupDto(Long id, String groupName) {
        this.id = id;
        GroupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupDto)) return false;

        UserGroupDto that = (UserGroupDto) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return !(getGroupName() != null ? !getGroupName().equals(that.getGroupName()) : that.getGroupName() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getGroupName() != null ? getGroupName().hashCode() : 0);
        return result;
    }
}

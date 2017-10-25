package ir.mimdolt.store.web.dto.product.attribute;

/**
 * Created by Hadi Jeddi Zahed on 2/19/2017.
 */
public class OptionValueDisplay {

    private Long id;
    private String title;
    private String classValue;

    public OptionValueDisplay() {
    }

    public OptionValueDisplay(Long id, String title, String classValue) {
        this.id = id;
        this.title = title;
        this.classValue = classValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassValue() {
        return classValue;
    }

    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionValueDisplay)) return false;

        OptionValueDisplay that = (OptionValueDisplay) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return !(getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }
}

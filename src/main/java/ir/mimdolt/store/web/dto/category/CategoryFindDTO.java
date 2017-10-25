package ir.mimdolt.store.web.dto.category;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */
public class CategoryFindDTO extends CategoryDTO {


    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}

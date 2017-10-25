package ir.mimdolt.store.web.dto.category;

/**
 * Created by Hadi Jeddi Zahed on 2/16/2017.
 */
public class FlatCategoryDto {

    private Long id;
    private String name;

    public FlatCategoryDto() {
    }

    public FlatCategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
}

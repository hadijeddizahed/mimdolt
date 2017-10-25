package ir.mimdolt.store.web.dto.product;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */
public class ProductSelectBoxDto {

    private Long id;
    private String title;

    public ProductSelectBoxDto() {
    }

    public ProductSelectBoxDto(Long id, String title) {
        this.id = id;
        this.title = title;
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
}

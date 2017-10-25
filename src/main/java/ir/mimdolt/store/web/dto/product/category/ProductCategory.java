package ir.mimdolt.store.web.dto.product.category;

/**
 * Created by Hadi Jeddi Zahed on 3/22/2017.
 */
public class ProductCategory {

    private Long id;
    private String title;

    public ProductCategory() {
    }

    public ProductCategory(Long id, String title) {
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

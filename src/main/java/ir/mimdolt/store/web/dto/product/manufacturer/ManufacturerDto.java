package ir.mimdolt.store.web.dto.product.manufacturer;

/**
 * Created by Hadi Jeddi Zahed on 2/13/2017.
 */
public class ManufacturerDto {

    private Long id;
    private String name;
    private Integer sortOrder = new Integer(0);
    private String  code;

    public ManufacturerDto() {
    }

    public ManufacturerDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ManufacturerDto(String name, Integer sortOrder, String code) {
        this.name = name;
        this.sortOrder = sortOrder;
        this.code = code;
    }

    public ManufacturerDto(Long id, String name, Integer sortOrder, String code) {
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.code = code;
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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

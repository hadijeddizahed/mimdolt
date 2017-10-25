package ir.mimdolt.store.web.dto.product;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */
public class ProductOptionValueDto {

    private Long id;
    private String optionTitle;
    private Long optionId;
    private Long optionValueId;
    private Integer quantity;
    private Double price;
    private Double weight;
    private int viewType;

    public ProductOptionValueDto() {
    }

    public ProductOptionValueDto(Long optionId, Long optionValueId, Integer quantity, Double price, int viewType) {
        this.optionId = optionId;
        this.optionValueId = optionValueId;
        this.quantity = quantity;
        this.price = price;
        this.viewType = viewType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getOptionValueId() {
        return optionValueId;
    }

    public void setOptionValueId(Long optionValueId) {
        this.optionValueId = optionValueId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}

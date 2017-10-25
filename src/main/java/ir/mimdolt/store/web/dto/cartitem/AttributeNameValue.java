package ir.mimdolt.store.web.dto.cartitem;

/**
 * Created by Hadi Jeddi Zahed on 2/21/2017.
 */
public class AttributeNameValue {
    private Long optionId;
    private String optionName;
    private String optionValue;

    public AttributeNameValue() {
    }


    public AttributeNameValue(Long optionId, String optionName, String optionValue) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionValue = optionValue;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}

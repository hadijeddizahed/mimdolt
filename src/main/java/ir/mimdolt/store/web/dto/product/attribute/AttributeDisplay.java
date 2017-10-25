package ir.mimdolt.store.web.dto.product.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/19/2017.
 */
public class AttributeDisplay {

    private String name;
    private String type;
    private List<OptionValueDisplay> optionValueDisplays = new ArrayList<>();

    public AttributeDisplay() {
    }

    public AttributeDisplay(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionValueDisplay> getOptionValueDisplays() {
        return optionValueDisplays;
    }

    public void setOptionValueDisplays(List<OptionValueDisplay> optionValueDisplays) {
        this.optionValueDisplays = optionValueDisplays;
    }
}

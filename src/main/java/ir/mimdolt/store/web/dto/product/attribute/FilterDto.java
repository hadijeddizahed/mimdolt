package ir.mimdolt.store.web.dto.product.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 4/21/2017.
 */
public class FilterDto {

    private String optionTitle;
    private List<OptionValueDisplay> optionValues = new ArrayList<>();


    public FilterDto() {
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public List<OptionValueDisplay> getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(List<OptionValueDisplay> optionValues) {
        this.optionValues = optionValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterDto)) return false;

        FilterDto filterDto = (FilterDto) o;

        if (!getOptionTitle().equals(filterDto.getOptionTitle())) return false;
        return getOptionValues().equals(filterDto.getOptionValues());

    }

    @Override
    public int hashCode() {
        int result = getOptionTitle().hashCode();
        result = 31 * result + getOptionValues().hashCode();
        return result;
    }
}

package ir.mimdolt.store.web.dto.product.attribute;

import ir.mimdolt.store.web.dto.product.ProductOptionValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */
public class AttributeDto {

    private List<ProductOptionValueDto> productOptionValue = new ArrayList<>();


    public List<ProductOptionValueDto> getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(List<ProductOptionValueDto> productOptionValue) {
        this.productOptionValue = productOptionValue;
    }
}

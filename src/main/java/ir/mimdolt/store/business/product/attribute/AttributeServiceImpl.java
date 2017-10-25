package ir.mimdolt.store.business.product.attribute;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.persist.entity.category.CategoryDao;
import ir.mimdolt.store.persist.entity.option.Option;
import ir.mimdolt.store.persist.entity.option.OptionDao;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValueDao;
import ir.mimdolt.store.persist.entity.product.attribute.Attribute;
import ir.mimdolt.store.persist.entity.product.attribute.AttributeDao;
import ir.mimdolt.store.persist.entity.product.attribute.AttributeViewType;
import ir.mimdolt.store.web.dto.product.ProductOptionValueDto;
import ir.mimdolt.store.web.dto.product.attribute.AttributeDto;
import ir.mimdolt.store.web.dto.product.attribute.FilterDto;
import ir.mimdolt.store.web.dto.product.attribute.OptionValueDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class AttributeServiceImpl extends AbstractServiceImpl<AttributeDto, Attribute, Long> implements AttributeService {

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private OptionValueDao optionValueDao;

    @Autowired
    private CategoryDao categoryDao;


    @Override
    public void saveOrUpdate(AttributeDto attributeDto) throws Exception {
        Attribute attribute = new Attribute();
        for (ProductOptionValueDto productOptionValueDto : attributeDto.getProductOptionValue()) {
            Option option = optionDao.find(productOptionValueDto.getOptionId());
            OptionValue optionValue = optionValueDao.find(productOptionValueDto.getOptionValueId());
            attribute.setPrice(productOptionValueDto.getPrice());
            attribute.setQuantity(productOptionValueDto.getQuantity());
            if (productOptionValueDto.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue())
                attribute.setViewType(AttributeViewType.PRODUCT_PAGE.getValue());
            else if (productOptionValueDto.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue())
                attribute.setViewType(AttributeViewType.FILTER_PANEL.getValue());
            else if (productOptionValueDto.getViewType() == AttributeViewType.BOTH.getValue())
                attribute.setViewType(AttributeViewType.BOTH.getValue());

            attributeDao.saveOrUpdate(attribute);

        }
    }

    @Override
    public Set<FilterDto> findByCategory(String code) throws Exception {
        Set<FilterDto> filterDtos = new HashSet<>();
        List<Attribute> attributes = attributeDao.findByCategory(code);
        FilterDto filterDto = null;
        for (Attribute attribute : attributes) {
            filterDto = new FilterDto();
            filterDto.setOptionTitle(attribute.getOption().getName());
            for (OptionValue optionValue : attribute.getOption().getOptionValueSet()) {
                filterDto.getOptionValues().add(new OptionValueDisplay(optionValue.getId(), optionValue.getValue(), optionValue.getClassValue()));
            }
            filterDtos.add(filterDto);
        }

        return filterDtos;
    }
}

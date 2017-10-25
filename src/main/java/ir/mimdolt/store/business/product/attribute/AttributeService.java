package ir.mimdolt.store.business.product.attribute;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.product.attribute.Attribute;
import ir.mimdolt.store.web.dto.product.attribute.AttributeDto;
import ir.mimdolt.store.web.dto.product.attribute.FilterDto;

import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 2/17/2017.
 */
public interface AttributeService extends AbstractService<AttributeDto,Attribute,Long>{

    void saveOrUpdate(AttributeDto attributeDto) throws Exception;

    Set<FilterDto> findByCategory(String code) throws Exception;
}

package ir.mimdolt.store.business.product.manufacturer;


import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.product.manufacturer.Manufacturer;
import ir.mimdolt.store.web.dto.product.manufacturer.ManufacturerDto;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/13/2017.
 */
public interface ManufacturerService extends AbstractService<ManufacturerDto,Manufacturer,Long> {

    ManufacturerDto find(Long id)throws Exception;

    ManufacturerDto saveOrUpdate(ManufacturerDto manufacturerDto) throws Exception;

    List<ManufacturerDto> listAll() throws Exception;
}


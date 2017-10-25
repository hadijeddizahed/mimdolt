package ir.mimdolt.store.business.product.manufacturer;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.persist.entity.product.manufacturer.Manufacturer;
import ir.mimdolt.store.persist.entity.product.manufacturer.ManufacturerDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.product.manufacturer.ManufacturerDto;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/13/2017.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ManufacturerServiceImpl extends AbstractServiceImpl<ManufacturerDto, Manufacturer, Long> implements ManufacturerService {

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Override
    public ManufacturerDto find(Long id) throws Exception {
        Manufacturer manufacturer = manufacturerDao.find(id);
        if (manufacturer == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        ManufacturerDto manufacturerDto = new ManufacturerDto(manufacturer.getId(),manufacturer.getName(), manufacturer.getOrder(), manufacturer.getCode());
        return manufacturerDto;
    }

    @Override
    public ManufacturerDto saveOrUpdate(ManufacturerDto manufacturerDto) throws Exception {
        Manufacturer manufacturer = new Manufacturer();
        if (manufacturerDto.getId() == null) {
            manufacturer.setName(manufacturerDto.getName());
            manufacturer.setCode(RandomStringUtils.randomAlphanumeric(6).toString());
            manufacturer.setOrder(manufacturerDto.getSortOrder());
            manufacturerDao.saveOrUpdate(manufacturer);
        } else {
            manufacturer = manufacturerDao.find(manufacturerDto.getId());
            if (manufacturer == null)
                throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
            manufacturer.setName(manufacturerDto.getName());
            manufacturer.setOrder(manufacturerDto.getSortOrder());
            manufacturerDao.update(manufacturer);
        }
        return manufacturerDto;
    }

    @Override
    public List<ManufacturerDto> listAll() throws Exception {
        List<Manufacturer> manufacturers = manufacturerDao.getAll();
        List<ManufacturerDto> manufacturerDtos = new ArrayList<>();
        for (Manufacturer manufacturer:manufacturers){
            manufacturerDtos.add(new ManufacturerDto(manufacturer.getId(),manufacturer.getName(),manufacturer.getOrder(),manufacturer.getCode()));
        }
        return manufacturerDtos;
    }
}

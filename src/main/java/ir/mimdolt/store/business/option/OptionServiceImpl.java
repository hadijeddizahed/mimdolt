package ir.mimdolt.store.business.option;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.persist.entity.option.Option;
import ir.mimdolt.store.persist.entity.option.OptionDao;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValueDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.option.OptionDTO;
import ir.mimdolt.store.web.dto.option.OptionValueDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */
@Service
@Transactional
public class OptionServiceImpl extends AbstractServiceImpl<OptionDTO, Long, Option> implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private OptionValueDao optionValueDao;


    @Autowired
    private OptionMapper optionMapper;

    @Override
    public OptionDTO find(Long id) throws Exception {
        Option option = optionDao.find(id);
        if (option == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        OptionDTO optionDTO = new OptionDTO();
        optionDTO = optionMapper.map(option);
        return optionDTO;
    }

    @Override
    public OptionDTO saveOrUpdate(OptionDTO optionDTO) throws Exception {
        Option option = null;
        if (optionDTO.getId() != null)
            option = optionDao.find(optionDTO.getId());
        if (option == null) {
            option = new Option();
            option.setName(optionDTO.getName());
            option.setSortOrder(optionDTO.getSortOrder());
            option.setOptionType(optionDTO.getType());
            optionDao.saveOrUpdate(option);
            for (OptionValueDto optionValueDto : optionDTO.getValues()) {
                OptionValue optionValue = new OptionValue(optionValueDto.getValue(), optionValueDto.getClassValue(),
                        optionValueDto.getSortOrder(), optionValueDto.getDescription());
                optionValue.setOption(option);
                optionValueDao.saveOrUpdate(optionValue);
            }

        } else {
            optionDao.update(option);
            boolean found = false;
            for (OptionValue optionValue : option.getOptionValueSet()) {
                found = false;
                for (OptionValueDto optionValueDto : optionDTO.getValues()) {
                    if (optionValue.getId() == optionValueDto.getId()) {
                        optionValue.setValue(optionValueDto.getValue());
                        optionValue.setClassValue(optionValueDto.getClassValue());
                        optionValue.setSortOrder(optionValueDto.getSortOrder());
                        optionValue.setDescription(optionValueDto.getDescription());
                        optionValue.setOption(option);
                        optionValueDao.update(optionValue);
                        found = true;
                    } else if (optionValueDto.getId() == null) {
                        OptionValue optionValue1 = new OptionValue(optionValueDto.getValue(),
                                optionValueDto.getSortOrder(), optionValueDto.getDescription());
                        optionValue1.setOption(option);
                        optionValueDao.saveOrUpdate(optionValue1);
                        optionValueDto.setId(optionValue.getId());
                    }
                }
                if (!found) {
                    optionValueDao.remove(optionValue);
                }
            }
        }

        return optionDTO;
    }

    @Override
    public List<OptionDTO> listAll() throws Exception {
        List<Option> optionList = optionDao.getAll();
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (Option option : optionList) {
            optionDTOList.add(optionMapper.map(option));
        }
        return optionDTOList;
    }
}

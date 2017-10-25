package ir.mimdolt.store.business.option;

import ir.mimdolt.store.persist.entity.option.Option;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import ir.mimdolt.store.web.dto.option.OptionDTO;
import ir.mimdolt.store.web.dto.option.OptionValueDto;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Hadi Jeddi Zahed on 11/20/2016.
 */
@Service
public class OptionMapper {

    public Option map(OptionDTO optionDTO) throws InvocationTargetException, IllegalAccessException {
        Option option = new Option();
        Set<OptionValue> optionValueSet = new HashSet<>();
        option.setId(optionDTO.getId());
        option.setName(optionDTO.getName());
        option.setOptionType(optionDTO.getType());
        option.setSortOrder(optionDTO.getSortOrder());
        optionValueSet.addAll(optionDTO.getValues().stream().map(optionValueDto ->
                new OptionValue(optionValueDto.getValue(), optionValueDto.getSortOrder(), optionValueDto.getDescription())).collect(Collectors.toList()));
        option.setOptionValueSet(optionValueSet);
        return option;

    }

    public OptionDTO map(Option option) throws InvocationTargetException, IllegalAccessException {
        if (option == null)
            return new OptionDTO();
        OptionDTO optionDTO = new OptionDTO();
        Set<OptionValueDto> optionValues = new HashSet<>();
        optionDTO.setId(option.getId());
        optionDTO.setName(option.getName());
        optionDTO.setType(option.getOptionType());
        optionDTO.setSortOrder(option.getSortOrder());
        for (OptionValue optionValue : option.getOptionValueSet()) {
            optionValues.add(new OptionValueDto(optionValue.getId(),optionValue.getValue(),optionValue.getClassValue(), optionValue.getSortOrder(), optionValue.getDescription()));
        }
        optionDTO.setValues(optionValues);
        return optionDTO;
    }
}

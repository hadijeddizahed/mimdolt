package ir.mimdolt.store.business.option;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.option.Option;
import ir.mimdolt.store.web.dto.option.OptionDTO;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */
public interface OptionService extends AbstractService<OptionDTO, Long, Option> {

    OptionDTO find(Long id)throws Exception;

    OptionDTO saveOrUpdate(OptionDTO optionDTO) throws Exception;

    List<OptionDTO> listAll() throws Exception;
}

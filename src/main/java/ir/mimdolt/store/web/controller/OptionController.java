package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.option.OptionService;
import ir.mimdolt.store.web.dto.option.OptionDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/18/2016.
 */

@RestController
@RequestMapping("/api")
@Api(description = "Option management API")
public class OptionController {

    @Autowired
    private OptionService optionService;


    @RequestMapping(value = "/admin/option", method = RequestMethod.POST)
    public @ResponseBody
    void addOption(@Validated @RequestBody OptionDTO optionDTO) throws Exception {
        optionService.saveOrUpdate(optionDTO);
    }

    @RequestMapping(value = "/admin/option/find/{id}", method = RequestMethod.GET)
    public @ResponseBody
    OptionDTO find(@PathVariable Long id) throws Exception {
        return optionService.find(id);
    }


    @RequestMapping(value = "/admin/option", method = RequestMethod.GET)
    public @ResponseBody
    List<OptionDTO> listAll() throws Exception {
        return optionService.listAll();
    }

}

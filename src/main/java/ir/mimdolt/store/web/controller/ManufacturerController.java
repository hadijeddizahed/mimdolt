package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.product.manufacturer.ManufacturerService;
import ir.mimdolt.store.web.dto.product.manufacturer.ManufacturerDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/13/2017.
 */
@RestController
@RequestMapping("/api")
@Api(description = "Option management API")
public class ManufacturerController {

        @Autowired
        private ManufacturerService manufacturerService;


        @RequestMapping(value = "/admin/manufacturer", method = RequestMethod.POST)
        public @ResponseBody
        void addManufacturer(@Validated @RequestBody ManufacturerDto manufacturerDto) throws Exception {
            manufacturerService.saveOrUpdate(manufacturerDto);
        }

        @RequestMapping(value = "/admin/manufacturer/find/{id}", method = RequestMethod.GET)
        public @ResponseBody
        ManufacturerDto find(@PathVariable Long id) throws Exception {
            return manufacturerService.find(id);
        }


        @RequestMapping(value = "/admin/manufacturers", method = RequestMethod.GET)
        public @ResponseBody
        List<ManufacturerDto> listAll() throws Exception {
            return manufacturerService.listAll();
        }


}

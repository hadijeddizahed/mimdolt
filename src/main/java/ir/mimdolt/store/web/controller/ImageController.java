package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.productimage.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hadi Jeddi Zahed on 2/15/2017.
 */
@RestController
@RequestMapping("/api")
public class ImageController {



    @Autowired
    private ProductImageService productImageService;



    @RequestMapping(value = "/display/{code}/{size}/{name:.+}", method = RequestMethod.GET)
    public
    @ResponseBody
    byte[] paging(@PathVariable("code") String code, @PathVariable("size") String size, @PathVariable("name") String name) throws Exception {
        return productImageService.displayImage(code, size,name);
    }


}
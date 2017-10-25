package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.filemanager.FileManagerService;
import ir.mimdolt.store.business.productimage.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by Hadi Jeddi Zahed on 12/4/2016.
 */

@RestController
@RequestMapping("/api")
public class FileManagerController {

    @Autowired
    private FileManagerService fileManagerService;

    @Autowired
    private ProductImageService productImageService;

    private String REPOSITORY_BASE_PATH = "/tmp/Product/";


    @RequestMapping(value = "/fm/download/{path}", method = RequestMethod.GET)
    public
    @ResponseBody
    HashMap<String, String> download(@PathVariable("path") String path) throws Exception {
        HashMap hashMap = new HashMap<String, String>();
        String image = Base64.getEncoder().encodeToString(fileManagerService.download(null, path));
        hashMap.put("image", image);
        return hashMap;
    }

    @RequestMapping(value = "/admin/fm/delete/{path}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void remove(@PathVariable("path") String path) throws Exception {
        fileManagerService.remove(path);
    }

    @RequestMapping(value = "/admin/fm/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    void upload(@RequestParam("productId") Long id, HttpServletRequest request) throws Exception {


    }

}

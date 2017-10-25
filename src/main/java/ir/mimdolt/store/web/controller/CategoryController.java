package ir.mimdolt.store.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mimdolt.store.business.category.CategoryService;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.web.dto.category.CategoryBaseDto;
import ir.mimdolt.store.web.dto.category.CategoryDTO;
import ir.mimdolt.store.web.dto.category.FlatCategoryDto;
import ir.mimdolt.store.web.dto.productimage.ImageSize;
import io.swagger.annotations.Api;
import org.apache.commons.lang.RandomStringUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */

@RestController
@RequestMapping("/api")
@Api(description = "Users management API")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public
    @ResponseBody
    void addCategory(@RequestParam(value = "category") String category, HttpServletRequest request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CategoryBaseDto categoryDto = mapper.readValue(category, CategoryBaseDto.class);


        String rootDirectory = "/tmp/category/";
        String imageUrl, imageName;
        MultipartHttpServletRequest mRequest;
        mRequest = (MultipartHttpServletRequest) request;
        mRequest.getParameterMap();
        MultipartFile mFile = null;
        Path path;
        String code = RandomStringUtils.randomAlphanumeric(6).toString();

        mFile = mRequest.getFile("file");
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO = categoryDto.map();
        categoryDTO.setCode(code);

        if (mFile != null) {
            imageUrl = rootDirectory + code;
            imageName = mFile.getOriginalFilename();

            BufferedImage mediumImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(mFile.getBytes())), 330, 440);
            BufferedImage smallImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(mFile.getBytes())), 100, 100);

            File file = new File(imageUrl + File.separator + ImageSize.MEDIUM.getValue() + File.separator + imageName);
            path = Paths.get(file.getPath());
            Files.createDirectories(path.getParent());
            ImageIO.write(mediumImage, "png", file);

            File smallFile = new File(imageUrl + File.separator + ImageSize.SMALL.getValue() + File.separator + imageName);
            path = Paths.get(smallFile.getPath());
            Files.createDirectories(path.getParent());
            ImageIO.write(smallImage, "png", smallFile);
            categoryDTO.setImageName(imageName);
        }
        categoryService.saveOrUpdate(categoryDTO);
    }

//    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    void addCategory(@Validated @RequestBody CategoryDTO categoryDto) throws Exception {
//        categoryService.saveOrUpdate(categoryDto);
//    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public
    @ResponseBody
    List<CategoryDTO> listAll() throws Exception {
//        PagingData<CategoryFindDTO> categoryFindDTOPagingData = categoryService.paging(0,10);
        return categoryService.listAll();
    }

    @RequestMapping(value = "/category/flat", method = RequestMethod.GET)
    public
    @ResponseBody
    List<FlatCategoryDto> listAllAsFlat() throws Exception {
//        PagingData<CategoryFindDTO> categoryFindDTOPagingData = categoryService.paging(0,10);
        return categoryService.listAllAsFlat();
    }

    @RequestMapping(value = "/categoryBYQuery", method = RequestMethod.GET)
    public
    @ResponseBody
    List<FlatCategoryDto> listAllByQuery(@RequestParam String query) throws Exception {
        return categoryService.listByQuery(query);
    }


    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<CategoryDTO> listAllParent() throws Exception {
        return categoryService.listAll();
    }

    @RequestMapping(value = "/category/listChild", method = RequestMethod.GET)
    public
    @ResponseBody
    List<CategoryDTO> listAllChildes() throws Exception {
        return categoryService.listAllChilde();
    }



    @RequestMapping(value = "/admin/category/page/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    PagingData<CategoryDTO> paging(@PathVariable("page") int page) throws Exception {
        return categoryService.paging(page, 2);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    CategoryDTO find(@PathVariable("id") Long id) throws Exception {
        return categoryService.find(id);
    }

    @RequestMapping(value = "/displayImage/{code}/{size}/{name:.+}", method = RequestMethod.GET)
    public
    @ResponseBody
    byte[] paging(@PathVariable("code") String code, @PathVariable("size") String size, @PathVariable("name") String name) throws Exception {
        return categoryService.displayImage(code, size, name);
    }


}

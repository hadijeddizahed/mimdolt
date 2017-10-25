package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.product.ProductService;
import ir.mimdolt.store.business.product.attribute.AttributeService;
import ir.mimdolt.store.business.productimage.ProductImageService;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.web.dto.product.ProductDto;
import ir.mimdolt.store.web.dto.product.ProductSelectBoxDto;
import ir.mimdolt.store.web.dto.product.attribute.FilterDto;
import ir.mimdolt.store.web.dto.productimage.ImageSize;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;
import org.apache.commons.lang.RandomStringUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.*;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ProductImageService productImageService;

    private String root = "/mimdolt/Product/";


    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/admin/product", method = RequestMethod.POST)
    public
    @ResponseBody
    ProductDto addProduct(@RequestBody ProductDto productDto) throws Exception {
        return productService.saveOrUpdate(productDto);
    }

    @RequestMapping(value = "/product/find/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ProductDto find(@PathVariable("id") Long id) throws Exception {
        return productService.find(id);
    }

    @RequestMapping(value = "/product/findByCode/{code}", method = RequestMethod.GET)
    public
    @ResponseBody
    ProductDto findByCode(@PathVariable("code") String code) throws Exception {
        return productService.findByCode(code);
    }

    @RequestMapping(value = "/product/lastSeen", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ProductDto> lastSeen() throws Exception {
        return productService.getLastSeen();
    }


    @RequestMapping(value = "/product/page/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    PagingData<ProductDto> paging(@PathVariable("page") int page) throws Exception {
        return productService.paging(page, 12);
    }

    @RequestMapping(value = "/product/category/{code}/page/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    PagingData<ProductDto> pagingByCategory(@PathVariable("page") int page, @PathVariable("code") String code,
                                            @RequestParam(name = "option",required = false)Long[] option,
                                            @RequestParam(name = "pageSize",required = false,defaultValue = "9")int pageSize,
                                            @RequestParam(name = "orderBy",required = false)String orderBy,
                                            @RequestParam(name = "priceRange",required = false)String priceRange) throws Exception {
        List<Long> optionIds = new ArrayList<>();
        if (option != null)
            optionIds = Arrays.asList(option);
        return productService.paging(code, page, pageSize, optionIds);
    }

    @RequestMapping(value = "/product/filterPanel/{code}", method = RequestMethod.GET)
    public
    @ResponseBody
    Set<FilterDto> getFilterPanel( @PathVariable("code") String code) throws Exception {
        return attributeService.findByCategory(code);
    }
    @RequestMapping(value = "/product/getProductImages/{productId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ProductImageDto> getProductImages(@PathVariable("productId") Long id) throws Exception {
        return productService.getProductImages(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/admin/product/delete/{imageId}", method = RequestMethod.POST)
    public
    @ResponseBody
    void remove(@PathVariable("imageId") Long imageId) throws Exception {
        productImageService.removeImage(imageId, null);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/admin/product/changeStatus/{productId}", method = RequestMethod.POST)
    public
    @ResponseBody
    void changeStatus(@PathVariable("productId") Long productId) throws Exception {
        productService.changeStatus(productId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/admin/product/setDefaultImage/{productId}/image/{imageId}", method = RequestMethod.POST)
    public
    @ResponseBody
    void setDefaultImage(@PathVariable("productId") Long productId, @PathVariable("imageId") Long imageId) throws Exception {
        productService.setDefaultImage(productId, imageId);
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/admin/product/selectBox", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ProductSelectBoxDto> getProductSelectBox() throws Exception {
        return productService.listAll();
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/admin/product/uploadImage", method = RequestMethod.POST)
    public
    @ResponseBody
    void upload(@RequestParam("productId") Long id, HttpServletRequest request) throws Exception {
        String imageUrl = "", imageName = "";
        MultipartHttpServletRequest mRequest;
        mRequest = (MultipartHttpServletRequest) request;
        mRequest.getParameterMap();
        MultipartFile mFile = null;
        Path path;
        String code = RandomStringUtils.randomAlphanumeric(6).toString();

        Iterator<String> itr = mRequest.getFileNames();
        while (itr.hasNext()) {
            mFile = mRequest.getFile(itr.next());

            imageUrl = root + code;
            imageName = mFile.getOriginalFilename();

            BufferedImage mediumImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(mFile.getBytes())), Scalr.Mode.FIT_TO_WIDTH, 440, 550);
            BufferedImage smallImage = Scalr.resize(ImageIO.read(new ByteArrayInputStream(mFile.getBytes())), 168, 195);

            File file = new File(imageUrl + File.separator + ImageSize.MEDIUM.getValue() + File.separator + imageName);
            path = Paths.get(file.getPath());
            Files.createDirectories(path.getParent());
            ImageIO.write(mediumImage, "png", file);

            File smallFile = new File(imageUrl + File.separator + ImageSize.SMALL.getValue() + File.separator + imageName);
            path = Paths.get(smallFile.getPath());
            Files.createDirectories(path.getParent());
            ImageIO.write(smallImage, "png", smallFile);
            productImageService.saveOrUpdate(new ProductImageDto(id, imageUrl, code, imageName, mFile.getContentType()));

        }
    }
}

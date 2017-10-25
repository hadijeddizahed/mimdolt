package ir.mimdolt.store.business.productimage;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.persist.entity.product.ProductDao;
import ir.mimdolt.store.persist.entity.productimage.ProductImage;
import ir.mimdolt.store.persist.entity.productimage.ProductImageDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.product.ProductDto;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductImageServiceImpl extends AbstractServiceImpl<ProductImageDto, Long, ProductImage> implements ProductImageService {

    private final static  String BASE_PATH = "/tmp/Product/";

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public ProductDto saveOrUpdate(ProductImageDto productImageDto) throws Exception {
        Product product = productDao.find(productImageDto.getId());
        if (product == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        ProductImage productImage = new ProductImage();
        productImage.map(productImageDto, product);
        productImageDao.saveOrUpdate(productImage);

        return null;
    }

    @Override
    public byte[] displayImage(ProductImageDto productImageDto, String size) throws Exception {
        String path = productImageDto.getUrl() + File.separator + size + File.separator + productImageDto.getImageName();
        Path path1 = Paths.get(path);
        return Files.readAllBytes(path1);
    }

    @Override
    public byte[] displayImage(String code, String size, String name) throws Exception {
        String path = BASE_PATH + code + File.separator + size + File.separator + name;
        Path path1 = Paths.get(path);
        return Files.readAllBytes(path1);
    }

    @Override
    public void removeImage(Long id, String path) throws Exception {
        ProductImage productImage = productImageDao.find(id);
        if (productImage == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        productImage.setEnable(Boolean.FALSE);
        productImageDao.update(productImage);
    }
}

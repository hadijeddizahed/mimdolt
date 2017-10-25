package ir.mimdolt.store.business.productimage;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.productimage.ProductImage;
import ir.mimdolt.store.web.dto.product.ProductDto;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
public interface ProductImageService extends AbstractService<ProductImageDto,Long,ProductImage> {

     ProductDto saveOrUpdate(ProductImageDto productImageDto)throws Exception;

     void removeImage(Long id, String path)throws Exception;

     byte[]  displayImage(ProductImageDto productImageDto,String size) throws Exception;

     byte[]  displayImage(String url, String size, String name) throws Exception;

}

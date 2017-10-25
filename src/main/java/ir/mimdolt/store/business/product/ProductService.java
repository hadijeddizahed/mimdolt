package ir.mimdolt.store.business.product;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.web.dto.product.ProductDto;
import ir.mimdolt.store.web.dto.product.ProductSelectBoxDto;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
public interface ProductService extends AbstractService<ProductDto, Long, Product> {

    ProductDto find(Long id) throws Exception;

    ProductDto findByCode(String code) throws Exception;

    ProductDto saveOrUpdate(ProductDto productDto) throws Exception;

    void updateLastSeen(Long productId)throws Exception;

    List<ProductDto> getLastSeen() throws Exception;

    PagingData<ProductDto> paging(Integer offset, Integer maxResult) throws Exception;

    PagingData<ProductDto> paging(String code,Integer offset, Integer maxResult,List<Long> optionValue) throws Exception;

    List<ProductImageDto> getProductImages(Long productId) throws Exception;

    void setDefaultImage(Long productId, Long imageId) throws Exception;

    void changeStatus(Long productId)throws Exception;

    List<ProductSelectBoxDto> listAll() throws Exception;


}

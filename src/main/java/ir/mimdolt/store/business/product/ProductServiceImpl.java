package ir.mimdolt.store.business.product;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.business.filemanager.FileManagerService;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.persist.entity.category.Category;
import ir.mimdolt.store.persist.entity.category.CategoryDao;
import ir.mimdolt.store.persist.entity.option.Option;
import ir.mimdolt.store.persist.entity.option.OptionDao;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValue;
import ir.mimdolt.store.persist.entity.optionvalue.OptionValueDao;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.persist.entity.product.ProductDao;
import ir.mimdolt.store.persist.entity.product.attribute.Attribute;
import ir.mimdolt.store.persist.entity.product.attribute.AttributeDao;
import ir.mimdolt.store.persist.entity.product.attribute.AttributeViewType;
import ir.mimdolt.store.persist.entity.product.manufacturer.Manufacturer;
import ir.mimdolt.store.persist.entity.product.manufacturer.ManufacturerDao;
import ir.mimdolt.store.persist.entity.product.relationship.ProductRelationship;
import ir.mimdolt.store.persist.entity.product.relationship.ProductRelationshipDao;
import ir.mimdolt.store.persist.entity.productimage.ProductImage;
import ir.mimdolt.store.persist.entity.productimage.ProductImageDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.product.ProductDto;
import ir.mimdolt.store.web.dto.product.ProductOptionValueDto;
import ir.mimdolt.store.web.dto.product.ProductSelectBoxDto;
import ir.mimdolt.store.web.dto.product.attribute.AttributeDisplay;
import ir.mimdolt.store.web.dto.product.attribute.AttributeDto;
import ir.mimdolt.store.web.dto.product.attribute.OptionValueDisplay;
import ir.mimdolt.store.web.dto.product.category.ProductCategory;
import ir.mimdolt.store.web.dto.product.manufacturer.ManufacturerDto;
import ir.mimdolt.store.web.dto.product.relationship.ProductRelationshipDto;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends AbstractServiceImpl<ProductDto, Long, Product> implements ProductService {


    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    protected ProductImageDao productImageDao;

    @Autowired
    private ManufacturerDao manufacturerDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private OptionValueDao optionValueDao;

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private FileManagerService fileManagerService;

    @Autowired
    private ProductRelationshipDao productRelationshipDao;


    @Override
    public ProductDto find(Long id) throws Exception {
        Product product = productDao.find(id);
        if (product == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setEnable(product.isEnable());
        productDto.setTitle(product.getTitle());
        productDto.setCode(product.getCode());
        productDto.setPrice(product.getPrice());
        productDto.setShortDescription(product.getShortDescription());
        productDto.setFullDescription(product.getFullDescription());
        if (product.getManufacturer() != null)
            productDto.setManufacturer(new ManufacturerDto(product.getManufacturer().getId(), product.getManufacturer().getName()));
        for (Category category : product.getCategorySet()) {
            productDto.getCategories().add(new ProductCategory(category.getId(), category.getName()));
        }

        for (Attribute attribute : product.getAttributeSet()) {
            AttributeDisplay attributeDisplay = productDto.getAttributesMap().get(attribute.getOption().getName());
            if (attributeDisplay == null) {
                attributeDisplay = new AttributeDisplay(attribute.getOption().getName(), attribute.getOption().getOptionType());
                attributeDisplay.getOptionValueDisplays().add(new OptionValueDisplay(attribute.getId(), attribute.getOptionValue().getValue(),
                        attribute.getOptionValue().getClassValue()));
                productDto.getAttributesMap().put(attribute.getOption().getName(), attributeDisplay);
            } else {
                attributeDisplay.getOptionValueDisplays().add(new OptionValueDisplay(attribute.getId(), attribute.getOptionValue().getValue(),
                        attribute.getOptionValue().getClassValue()));
            }
        }

        Set<Long> optionValueIds = new HashSet<>();
        List<AttributeDto> attributes = new ArrayList<>();
        for (Attribute attribute : product.getAttributeSet()) {
            if (optionValueIds.add(attribute.getOption().getId())) {
                AttributeDto attributeDto = new AttributeDto();
                ProductOptionValueDto productOptionValueDto = new ProductOptionValueDto();
                productOptionValueDto.setId(attribute.getId());
                productOptionValueDto.setPrice(attribute.getPrice());
                productOptionValueDto.setOptionId(attribute.getOption().getId());
                productOptionValueDto.setOptionTitle(attribute.getOption().getName());
                productOptionValueDto.setOptionValueId(attribute.getOptionValue().getId());
                productOptionValueDto.setQuantity(attribute.getQuantity());
                productOptionValueDto.setWeight(attribute.getWeight());
                productOptionValueDto.setViewType(attribute.getViewType());
                attributeDto.getProductOptionValue().add(productOptionValueDto);
                attributes.add(attributeDto);
            } else {
                int index = getIndex(optionValueIds, attribute.getOption().getId());
                ProductOptionValueDto productOptionValueDto = new ProductOptionValueDto();
                productOptionValueDto.setId(attribute.getId());
                productOptionValueDto.setPrice(attribute.getPrice());
                productOptionValueDto.setOptionId(attribute.getOption().getId());
                productOptionValueDto.setOptionTitle(attribute.getOption().getName());
                productOptionValueDto.setOptionValueId(attribute.getOptionValue().getId());
                productOptionValueDto.setQuantity(attribute.getQuantity());
                productOptionValueDto.setWeight(attribute.getWeight());
                productOptionValueDto.setViewType(attribute.getViewType());
                attributes.get(index).getProductOptionValue().add(productOptionValueDto);
            }
        }
        productDto.setAttributes(attributes);

        for (ProductImage productImage : product.getProductImageList()) {
            productDto.getImages().add(new ProductImageDto(productImage.getUrl(), productImage.getCode(), productImage.getName(), productImage.getType()));
        }

        for (ProductRelationship relatedProduct : product.getRelationships()) {
            ProductRelationshipDto productRelationshipDto = new ProductRelationshipDto();
            productRelationshipDto.setId(relatedProduct.getRelatedProduct().getId());
            productRelationshipDto.setTitle(relatedProduct.getRelatedProduct().getTitle());


            productDto.getRelationshipProducts().add(productRelationshipDto);
        }
        return productDto;
    }

    @Override
    public ProductDto findByCode(String code) throws Exception {
        Product product = productDao.findByCode(code);
        if (product == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());

        product.setLastSeen(new Date());


        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setEnable(product.isEnable());
        productDto.setTitle(product.getTitle());
        productDto.setCode(product.getCode());
        productDto.setPrice(product.getPrice());
        productDto.setShortDescription(product.getShortDescription());
        productDto.setFullDescription(product.getFullDescription());
        if (product.getManufacturer() != null)
            productDto.setManufacturer(new ManufacturerDto(product.getManufacturer().getId(), product.getManufacturer().getName()));
        for (Category category : product.getCategorySet()) {
            productDto.getCategories().add(new ProductCategory(category.getId(), category.getName()));
        }

        for (Attribute attribute : product.getAttributeSet()) {
            AttributeDisplay attributeDisplay = productDto.getAttributesMap().get(attribute.getOption().getName());
            if (attributeDisplay == null) {
                attributeDisplay = new AttributeDisplay(attribute.getOption().getName(), attribute.getOption().getOptionType());
                attributeDisplay.getOptionValueDisplays().add(new OptionValueDisplay(attribute.getId(), attribute.getOptionValue().getValue(),
                        attribute.getOptionValue().getClassValue()));
                productDto.getAttributesMap().put(attribute.getOption().getName(), attributeDisplay);
            } else {
                attributeDisplay.getOptionValueDisplays().add(new OptionValueDisplay(attribute.getId(), attribute.getOptionValue().getValue(),
                        attribute.getOptionValue().getClassValue()));
            }
        }

        for (ProductImage productImage : product.getProductImageList()) {
            productDto.getImages().add(new ProductImageDto(productImage.getUrl(), productImage.getCode(), productImage.getName(), productImage.getType()));
        }
        for (ProductRelationship relatedProduct : product.getRelationships()) {
            ProductRelationshipDto productRelationshipDto = new ProductRelationshipDto();
            productRelationshipDto.setCode(relatedProduct.getRelatedProduct().getCode());
            productRelationshipDto.setTitle(relatedProduct.getRelatedProduct().getTitle());
            productRelationshipDto.setPrice(relatedProduct.getRelatedProduct().getPrice());
            if (relatedProduct.getRelatedProduct().getDefaultImage() != null)
                productRelationshipDto.setDefaultImage(new ProductImageDto(relatedProduct.getRelatedProduct().getDefaultImage().getUrl(),
                        relatedProduct.getRelatedProduct().getDefaultImage().getCode(),
                        relatedProduct.getRelatedProduct().getDefaultImage().getName(), relatedProduct.getRelatedProduct().getDefaultImage().getType()));

            productDto.getRelationshipProducts().add(productRelationshipDto);
        }
        return productDto;
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto productDto) throws Exception {
        Product product = new Product();
        Manufacturer manufacturer = null;
        if (productDto.getManufacturer() != null)
            manufacturer = manufacturerDao.find(productDto.getManufacturer().getId());
        if (productDto.getId() != null)
            product = productDao.find(productDto.getId());
        Set<Long> categoriesId = new HashSet<>();
        for (ProductCategory productCategory : productDto.getCategories()) {
            categoriesId.add(productCategory.getId());
        }
        List<Category> categoryList = categoryDao.listCategory(categoriesId);
        if (categoryList == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        product.map(productDto, new HashSet<>(categoryList));
        product.setManufacturer(manufacturer);
        if (productDto.getId() != null)
            productDao.update(product);
        else {
            productDao.saveOrUpdate(product);
            productDto.setId(product.getId());
        }


        List<ProductRelationship> newRelationList = new ArrayList<>();
        Set<ProductRelationship> oldRelations = product.getRelationships();
        List<Long> relationIds = new ArrayList<>();
        boolean newFound = false;
        for (ProductRelationship productRelationship : oldRelations) {
            relationIds.add(productRelationship.getRelatedProduct().getId());
        }
        for (ProductRelationshipDto relationshipDto : productDto.getRelationshipProducts()) {
            if (!relationIds.contains(relationshipDto.getId())) {
                ProductRelationship relationship = new ProductRelationship();
                Product relatedProduct = productDao.find(relationshipDto.getId());
                relationship.setCode(relatedProduct.getCode());
                relationship.setProduct(product);
                relationship.setRelatedProduct(relatedProduct);
                productRelationshipDao.add(relationship);
                product.getRelationships().add(relationship);
                newRelationList.add(relationship);
            }

        }
        if (!newRelationList.isEmpty()) {
            for (Iterator<ProductRelationship> it = oldRelations.iterator(); it.hasNext(); ) {
                ProductRelationship productRelationship = it.next();
                if (!newRelationList.contains(productRelationship)) {
                    product.getRelationships().remove(productRelationship);
                }
            }
        }


        List<Attribute> availbleAttributes = new ArrayList<>();
        for (AttributeDto attributeDto : productDto.getAttributes()) {
            for (ProductOptionValueDto productOptionValueDto : attributeDto.getProductOptionValue()) {
                Attribute attribute = new Attribute();
                if (productOptionValueDto.getId() == null) {

                    Option option = optionDao.find(productOptionValueDto.getOptionId());
                    OptionValue optionValue = optionValueDao.find(productOptionValueDto.getOptionValueId());
                    attribute.setOption(option);
                    attribute.setOptionValue(optionValue);
                    attribute.setProduct(product);
                    attribute.setPrice(productOptionValueDto.getPrice());
                    attribute.setQuantity(productOptionValueDto.getQuantity());
                    attribute.setWeight(productOptionValueDto.getWeight());
                    if (productOptionValueDto.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue())
                        attribute.setViewType(AttributeViewType.PRODUCT_PAGE.getValue());
                    else if (productOptionValueDto.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue())
                        attribute.setViewType(AttributeViewType.FILTER_PANEL.getValue());
                    else if (productOptionValueDto.getViewType() == AttributeViewType.BOTH.getValue())
                        attribute.setViewType(AttributeViewType.BOTH.getValue());
                    attributeDao.saveOrUpdate(attribute);
                    product.getAttributeSet().add(attribute);
                } else {
                    attribute = attributeDao.find(productOptionValueDto.getId());
                    OptionValue optionValue = optionValueDao.find(productOptionValueDto.getOptionValueId());
                    attribute.setOptionValue(optionValue);
                    attribute.setProduct(product);
                    attribute.setPrice(productOptionValueDto.getPrice());
                    attribute.setQuantity(productOptionValueDto.getQuantity());
                    attribute.setWeight(productOptionValueDto.getWeight());
                    if (productOptionValueDto.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue())
                        attribute.setViewType(AttributeViewType.PRODUCT_PAGE.getValue());
                    else if (productOptionValueDto.getViewType() == AttributeViewType.PRODUCT_PAGE.getValue())
                        attribute.setViewType(AttributeViewType.FILTER_PANEL.getValue());
                    else if (productOptionValueDto.getViewType() == AttributeViewType.BOTH.getValue())
                        attribute.setViewType(AttributeViewType.BOTH.getValue());
                    attributeDao.update(attribute);

                }
                availbleAttributes.add(attribute);
            }
        }

        Set<Attribute> attributes = product.getAttributeSet();
        for (Attribute attribute : attributes) {
            if (!availbleAttributes.contains(attribute)) {
                attributeDao.remove(attribute);
            }
        }

        return productDto;
    }

    @Override
    public void updateLastSeen(Long productId) throws Exception {
        Product product = productDao.find(productId);
        if (product != null){
            product.setLastSeen(new Date());
            productDao.update(product);
        }
    }

    @Override
    public List<ProductDto> getLastSeen() throws Exception {
        List<Product> products = productDao.getLastSeen();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product:products){
            productDtoList.add(new ProductDto().map(product));
        }
        return productDtoList;
    }

    @Override
    public PagingData<ProductDto> paging(Integer offset, Integer maxResult) throws Exception {
        PagingData<Product> productPagingData = productDao.paging(offset, maxResult,null,null);
        PagingData<ProductDto> pagingData = new PagingData<>();
        List<ProductDto> productDtoList = new ArrayList<>();
        productPagingData.getData().forEach(product -> {
//            if (product.isEnable()) {
                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setTitle(product.getTitle());
                productDto.setCode(product.getCode());
                productDto.setEnable(product.isEnable());
                if (product.getDefaultImage() != null)
                    productDto.setDefaultImage(new ProductImageDto(product.getDefaultImage().getUrl(),
                            product.getDefaultImage().getCode(), product.getDefaultImage().getName(), product.getDefaultImage().getType()));
                productDtoList.add(productDto);
//            }
        });
        pagingData.setData(productDtoList);
        pagingData.setCount(productPagingData.getCount());
        return pagingData;
    }

    @Override
    public PagingData<ProductDto> paging(String code, Integer offset, Integer maxResult,List<Long> optionValue) throws Exception {
        PagingData<Product> productPagingData = productDao.paging(offset, maxResult,optionValue,code);
        Category category = categoryDao.findByCode(code);
        PagingData<ProductDto> pagingData = new PagingData<>();
        List<ProductDto> productDtoList = new ArrayList<>();
        productPagingData.getData().forEach(product -> {
            if (product.isEnable()) {
                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setTitle(product.getTitle());
                productDto.setCode(product.getCode());
                productDto.setPrice(product.getPrice());
                productDto.setEnable(product.isEnable());
                if (product.getDefaultImage() != null)
                    productDto.setDefaultImage(new ProductImageDto(product.getDefaultImage().getUrl(),
                            product.getDefaultImage().getCode(), product.getDefaultImage().getName(), product.getDefaultImage().getType()));
                productDtoList.add(productDto);
            }
        });
        pagingData.setData(productDtoList);
        pagingData.setCount(productPagingData.getCount());
        return pagingData;
    }

    @Override
    public List<ProductImageDto> getProductImages(Long productId) throws Exception {
        Product product = productDao.find(productId);
        if (product == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        List<ProductImageDto> productImageDtos = new ArrayList<>();
        if (product.getProductImageList() != null && !product.getProductImageList().isEmpty()) {
            product.getProductImageList().forEach(productImage -> {
                try {
                    if (productImage.isEnable()) {
                        productImageDtos.add(new ProductImageDto().map(productImage));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return productImageDtos;
    }

    @Override
    public void setDefaultImage(Long productId, Long imageId) throws Exception {
        Product product = productDao.find(productId);
        if (product == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        ProductImage productImage = productImageDao.find(imageId);
        if (productImage == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), ProductImage.class.getName());
        product.setDefaultImage(productImage);
        productDao.update(product);
    }

    @Override
    public void changeStatus(Long productId) throws Exception {
        Product product = productDao.find(productId);
        if (product == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        product.setEnable(!product.isEnable());
        productDao.update(product);
    }

    @Override
    public List<ProductSelectBoxDto> listAll() throws Exception {
        List<Product> products = productDao.getAll();
        List<ProductSelectBoxDto> productSelectBoxDtos = new ArrayList<>();
        for (Product product : products) {
            productSelectBoxDtos.add(new ProductSelectBoxDto(product.getId(), product.getTitle()));
        }
        return productSelectBoxDtos;
    }

    private int getIndex(Set<? extends Object> set, Object value) {
        int result = 0;
        for (Object entry : set) {
            if (entry.equals(value)) return result;
            result++;
        }
        return -1;
    }
}

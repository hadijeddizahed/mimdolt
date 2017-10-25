package ir.mimdolt.store.web.dto.productimage;

import ir.mimdolt.store.persist.entity.productimage.ProductImage;

/**
 * Created by Hadi Jeddi Zahed on 11/30/2016.
 */
public class ProductImageDto {


    private Long id;
    private String url;
    private String thumbnailUrl;
    private Long productId;
    private Boolean defaultImage;
    private String source;
    private String code;
    private String imageName;
    private String type;

    public ProductImageDto() {
    }

    public ProductImageDto(String url, String thumbnailUrl, Long productId, Boolean defaultImage) {
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.productId = productId;
        this.defaultImage = defaultImage;
    }

    public ProductImageDto(String url, String code, String imageName,String type) {
        this.url = url;
        this.code = code;
        this.imageName = imageName;
        this.type = type;
    }
    public ProductImageDto(String url, Long productId, String code, String imageName) {
        this.url = url;
        this.productId = productId;
        this.code = code;
        this.imageName = imageName;
    }

    public ProductImageDto(Long id, String url, String code, String imageName, String type) {
        this.id = id;
        this.url = url;
        this.code = code;
        this.imageName = imageName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Boolean isDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Boolean defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProductImageDto map(ProductImage productImage) throws Exception {
        if (productImage == null)
            return new ProductImageDto();
        this.setId(productImage.getId());
        this.setImageName(productImage.getName());
        this.setCode(productImage.getCode());
        this.setUrl(productImage.getUrl());
        this.setProductId(productImage.getProduct().getId());
        if (productImage.getOwnerProduct() != null) {
            this.setDefaultImage(Boolean.TRUE);
        } else this.setDefaultImage(Boolean.FALSE);
        return this;
    }

}

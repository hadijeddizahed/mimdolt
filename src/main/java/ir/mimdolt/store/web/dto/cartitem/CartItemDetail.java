package ir.mimdolt.store.web.dto.cartitem;

import ir.mimdolt.store.web.dto.productimage.ProductImageDto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 1/26/2017.
 */
public class CartItemDetail {
    private Long productId;
    private Long cartItemId;
    private String code;
    private String productName;
    private int quantity;
    private double price;
    private ProductImageDto defaultImage;
    private Set<AttributeNameValue> attributeNameValues = new HashSet<>();


    public CartItemDetail() {
    }

    public CartItemDetail(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductImageDto getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ProductImageDto defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Set<AttributeNameValue> getAttributeNameValues() {
        return attributeNameValues;
    }

    public void setAttributeNameValues(Set<AttributeNameValue> attributeNameValues) {
        this.attributeNameValues = attributeNameValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemDetail)) return false;

        CartItemDetail that = (CartItemDetail) o;

        if (getProductId() != null ? !getProductId().equals(that.getProductId()) : that.getProductId() != null)
            return false;
        if (getCartItemId() != null ? !getCartItemId().equals(that.getCartItemId()) : that.getCartItemId() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(that.getProductName()) : that.getProductName() != null)
            return false;
        return !(getAttributeNameValues() != null ? !getAttributeNameValues().equals(that.getAttributeNameValues()) : that.getAttributeNameValues() != null);

    }

    @Override
    public int hashCode() {
        int result = getProductId() != null ? getProductId().hashCode() : 0;
        result = 31 * result + (getCartItemId() != null ? getCartItemId().hashCode() : 0);
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getAttributeNameValues() != null ? getAttributeNameValues().hashCode() : 0);
        return result;
    }
}

package ir.mimdolt.store.web.dto.cartitem;

/**
 * Created by Hadi Jeddi Zahed on 2/20/2017.
 */
public class CartItemAttributeDto {

    private Long attributeId;
    private Long cartItemId;

    public CartItemAttributeDto() {
    }

    public CartItemAttributeDto(Long attributeId, Long cartItemId) {
        this.attributeId = attributeId;
        this.cartItemId = cartItemId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }
}

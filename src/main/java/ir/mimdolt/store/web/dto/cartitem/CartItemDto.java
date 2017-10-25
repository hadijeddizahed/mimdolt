package ir.mimdolt.store.web.dto.cartitem;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */
public class CartItemDto {

    private Long cartItemId;
    private Long productId;
    private int quantity;
    private Set<Long> cartItemAttribute = new HashSet<>();

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Long> getCartItemAttribute() {
        return cartItemAttribute;
    }

    public void setCartItemAttribute(Set<Long> cartItemAttribute) {
        this.cartItemAttribute = cartItemAttribute;
    }
}

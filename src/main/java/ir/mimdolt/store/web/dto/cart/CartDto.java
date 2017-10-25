package ir.mimdolt.store.web.dto.cart;

import ir.mimdolt.store.web.dto.cartitem.CartItemDto;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */
public class CartDto {

    private CartItemDto cartItem;
    private String code;

    public CartItemDto getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItemDto cartItem) {
        this.cartItem = cartItem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

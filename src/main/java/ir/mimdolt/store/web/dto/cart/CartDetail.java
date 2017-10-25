package ir.mimdolt.store.web.dto.cart;

import ir.mimdolt.store.web.dto.cartitem.CartItemDetail;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 1/26/2017.
 */
public class CartDetail {

    private Set<CartItemDetail>  cart = new HashSet<>();

    public Set<CartItemDetail> getCart() {
        return cart;
    }

    public void setCart(Set<CartItemDetail> cart) {
        this.cart = cart;
    }
}

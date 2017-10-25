package ir.mimdolt.store.business.cart;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.cart.Cart;
import ir.mimdolt.store.web.dto.cart.CartDetail;
import ir.mimdolt.store.web.dto.cart.CartDto;
import ir.mimdolt.store.web.dto.cartitem.CartItemDto;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */


public interface CartService extends AbstractService<CartDto, Long, Cart> {

    CartDto find(Long id) throws Exception;

    CartDto addToCart(CartDto cartDto) throws Exception;

    void deleteItem(String code) throws Exception;

    void update(CartDto cartDto) throws Exception;

    CartDetail getCart(String code) throws Exception;

    void updateCartItem(CartItemDto cartItemDto) throws Exception;
}

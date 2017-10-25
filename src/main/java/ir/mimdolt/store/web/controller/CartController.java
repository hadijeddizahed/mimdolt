package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.cart.CartService;
import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.web.dto.cart.CartDetail;
import ir.mimdolt.store.web.dto.cart.CartDto;
import ir.mimdolt.store.web.dto.cartitem.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public
    @ResponseBody
    void addToCart(@Validated @RequestBody CartDto cartDto, HttpServletRequest request,
                   HttpServletResponse response) throws Exception {
        boolean isFind = false;
        Cookie cookie[] = request.getCookies();
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals("SHOPPING_CART")) {
                cartDto.setCode(cookie1.getValue());
                isFind = true;
            }
        }

        cartDto = cartService.addToCart(cartDto);
        if (!isFind) {
            Cookie cook = new Cookie("SHOPPING_CART", cartDto.getCode());
            cook.setPath("/");
            cook.setMaxAge(60 * 24 * 3600);
            response.addCookie(cook);
        }


    }

    @RequestMapping(value = "/cart/display", method = RequestMethod.GET)
    public
    @ResponseBody
    CartDetail displayCart(@CookieValue(value = "SHOPPING_CART", required = false) String code) throws Exception {
        return cartService.getCart(code);
    }

    @RequestMapping(value = "/cart/deleteItem/{code}", method = RequestMethod.POST)
    public
    @ResponseBody
    void deleteItem(@PathVariable(value = "code") String code) throws Exception {
        cartService.deleteItem(code);
    }


    @RequestMapping(value = "/cart/updateCartItem", method = RequestMethod.POST)
    public
    @ResponseBody
    void updateCartItem(@Validated @RequestBody CartItemDto cartItemDto) throws Exception {
        cartService.updateCartItem(cartItemDto);
    }


}

package ir.mimdolt.store.business.cart;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.persist.entity.cart.Cart;
import ir.mimdolt.store.persist.entity.cart.CartDao;
import ir.mimdolt.store.persist.entity.cartitem.CartItem;
import ir.mimdolt.store.persist.entity.cartitem.CartItemDao;
import ir.mimdolt.store.persist.entity.cartitemattribute.CartItemAttribute;
import ir.mimdolt.store.persist.entity.cartitemattribute.CartItemAttributeDao;
import ir.mimdolt.store.persist.entity.product.Product;
import ir.mimdolt.store.persist.entity.product.ProductDao;
import ir.mimdolt.store.persist.entity.product.attribute.Attribute;
import ir.mimdolt.store.persist.entity.product.attribute.AttributeDao;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.cart.CartDetail;
import ir.mimdolt.store.web.dto.cart.CartDto;
import ir.mimdolt.store.web.dto.cartitem.AttributeNameValue;
import ir.mimdolt.store.web.dto.cartitem.CartItemDetail;
import ir.mimdolt.store.web.dto.cartitem.CartItemDto;
import ir.mimdolt.store.web.dto.productimage.ProductImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Hadi Jeddi Zahed on 1/24/2017.
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class CartServiceImpl extends AbstractServiceImpl<CartDto, Long, Cart> implements CartService {


    @Autowired
    private UserService userService;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartItemAttributeDao cartItemAttributeDao;

    @Autowired
    private AttributeDao attributeDao;

    @Override
    public CartDto find(Long id) throws Exception {
        return null;
    }

    @Override
    public CartDto addToCart(CartDto cartDto) throws Exception {
        User currentUser = userService.getLoggedInUser();
        Cart userCart = null;
        Cart sessionCart = null;
        Cart mainCart = null;

        if (cartDto.getCode() != null) {
            sessionCart = cartDao.findByCode(cartDto.getCode());
        }
        if (sessionCart == null && currentUser == null) {
            sessionCart = new Cart();
            UUID cartCode = UUID.randomUUID();
            if (cartDto.getCode() == null || cartDto.getCode().equals(""))
                cartDto.setCode(cartCode.toString().replaceAll("-", ""));
            sessionCart.setCode(cartDto.getCode());
            cartDao.saveOrUpdate(sessionCart);
        }

        if (currentUser != null) {
            userCart = cartDao.find(currentUser);
            if (userCart == null) {
                userCart = new Cart();
                userCart.setUser(currentUser);
                cartDao.saveOrUpdate(userCart);
            }
        }


        boolean repeated = false;
        boolean inUserCart = false;
        Product product = null;
        CartItem cartItem = new CartItem();
        product = productDao.find(cartDto.getCartItem().getProductId());
        if (currentUser != null) {
            if (!userCart.getCartItems().isEmpty()) {
                for (CartItem cartItem1 : userCart.getCartItems()) {
                    if (!repeated) {
                        if (cartItem1.getProduct().getId() == product.getId()) {
                            for (CartItemAttribute cartItemAttribute : cartItem1.getCartItemAttributes()) {
                                if (!cartDto.getCartItem().getCartItemAttribute().contains(cartItemAttribute.getAttributeId())) {
                                    repeated = false;
                                } else repeated = true;
                            }
                            if (repeated) {
                                cartItem1.setQuantity(cartItem1.getQuantity() + cartDto.getCartItem().getQuantity());
                                cartItemDao.update(cartItem1);
                                inUserCart = true;
                            }
                        }
                    }
                }
            }
            if (!sessionCart.getCartItems().isEmpty()) {
                for (CartItem cartItem1 : sessionCart.getCartItems()) {
                    if (!repeated) {
                        if (cartItem1.getProduct().equals(product)) {
                            if (!cartItem1.getCartItemAttributes().isEmpty()) {
                                for (CartItemAttribute cartItemAttribute : cartItem1.getCartItemAttributes()) {
                                    if (!cartDto.getCartItem().getCartItemAttribute().contains(cartItemAttribute.getAttributeId())) {
                                        repeated = false;
                                    } else repeated = true;
                                }
                            } else repeated = true;
                            if (repeated && !inUserCart) {
                                cartItem1.setQuantity(cartItem1.getQuantity() + cartDto.getCartItem().getQuantity());
                                cartItem1.setCart(userCart);
                                cartItemDao.update(cartItem1);
                            }

                        }
                    }
                }
                for (CartItem cartItem1 : sessionCart.getCartItems()) {
                    cartItem1.setCart(userCart);
                }
            }


            mainCart = userCart;
        } else {
            if (!sessionCart.getCartItems().isEmpty()) {
                for (CartItem cartItem1 : sessionCart.getCartItems()) {
                    if (cartItem1.getProduct().equals(product)) {
                        if (!cartItem1.getCartItemAttributes().isEmpty()) {
                            for (CartItemAttribute cartItemAttribute : cartItem1.getCartItemAttributes()) {
                                if (!cartDto.getCartItem().getCartItemAttribute().contains(cartItemAttribute.getAttributeId())) {
                                    repeated = false;
                                } else repeated = true;
                            }
                        } else repeated = true;
                        if (repeated) {
                            cartItem1.setQuantity(cartItem1.getQuantity() + cartDto.getCartItem().getQuantity());
                            cartItemDao.update(cartItem1);
                        }

                    }
                }
            }
            mainCart = sessionCart;
        }
        if (!repeated) {
            UUID cartItemCode = UUID.randomUUID();
            cartItem.setProduct(product);
            cartItem.setCode(cartItemCode.toString().replaceAll("-", ""));
            cartItem.setQuantity(cartDto.getCartItem().getQuantity());
            cartItem.setCart(mainCart);
//            cartItem.setTotalPrice(product.getPrice());
            cartItemDao.saveOrUpdate(cartItem);
            for (Long aLong : cartDto.getCartItem().getCartItemAttribute()) {
                CartItemAttribute cartItemAttribute = new CartItemAttribute();
                cartItemAttribute.setAttributeId(aLong);
                cartItemAttribute.setCartItem(cartItem);
                cartItemAttributeDao.saveOrUpdate(cartItemAttribute);
            }
        }
        return cartDto;


    }

    @Override
    public void deleteItem(String code) throws Exception {
        CartItem cartItem = cartItemDao.findByCode(code);
        for (CartItemAttribute cartItemAttribute : cartItem.getCartItemAttributes()) {
            cartItemAttributeDao.remove(cartItemAttribute);
        }
        cartItemDao.remove(cartItem);
    }

    @Override
    public void update(CartDto cartDto) throws Exception {

    }

    @Override
    public CartDetail getCart(String code) throws Exception {
        User user = userService.getLoggedInUser();
        Cart userCart = null;
        Cart sessionCart = null;
        Set<CartItem> userCartItems = null;
        Set<CartItem> sessionCartItems = null;
        if (user != null) {
            userCart = cartDao.find(user);
            if (null != userCart)
                userCartItems = userCart.getCartItems();
        }
        if (code != null) {
            sessionCart = cartDao.findByCode(code);
            if (sessionCart != null)
                sessionCartItems = sessionCart.getCartItems();
        }
        return mergeCartItem(userCartItems, sessionCartItems);

    }


    private CartDetail mergeCartItem(Set<CartItem> userCartItems, Set<CartItem> sessionCartItems) throws Exception {
        CartDetail cartDetail = new CartDetail();
        Set<CartItem> allCartItems = new HashSet<>();
        Set<CartItemDetail> cartItemDetails = new HashSet<>();
        CartItemDetail cartItemDetail = null;
        if (sessionCartItems != null)
            allCartItems.addAll(sessionCartItems);
        if (userCartItems != null)
            allCartItems.addAll(userCartItems);

        for (CartItem cartItem : allCartItems) {
            CartItem ucart = hasProduct(sessionCartItems, cartItem.getProduct());
            CartItem scart = hasProduct(userCartItems, cartItem.getProduct());
            if (ucart != null && scart != null) {
                cartItemDetail = new CartItemDetail();
                cartItemDetail.setCode(cartItem.getCode());
                cartItemDetail.setQuantity(ucart.getQuantity() + scart.getQuantity());
                cartItemDetail.setCartItemId(cartItem.getId());
                cartItemDetail.setPrice(cartItem.getProduct().getPrice());
                cartItemDetail.setProductName(cartItem.getProduct().getTitle());
                cartItemDetail.setProductId(cartItem.getProduct().getId());
                cartItemDetail.setDefaultImage(new ProductImageDto(cartItem.getProduct().getDefaultImage().getUrl(),
                        cartItem.getProduct().getDefaultImage().getCode(), cartItem.getProduct().getDefaultImage().getName(),
                        cartItem.getProduct().getDefaultImage().getType()));
                cartItemDetails.add(cartItemDetail);
            } else {
                cartItemDetail = new CartItemDetail();
                cartItemDetail.setCode(cartItem.getCode());
                cartItemDetail.setProductId(cartItem.getProduct().getId());
                cartItemDetail.setCartItemId(cartItem.getId());
                cartItemDetail.setQuantity(cartItem.getQuantity());
                cartItemDetail.setPrice(cartItem.getProduct().getPrice());
                cartItemDetail.setProductName(cartItem.getProduct().getTitle());
                cartItemDetail.setDefaultImage(new ProductImageDto(cartItem.getProduct().getDefaultImage().getUrl(),
                        cartItem.getProduct().getDefaultImage().getCode(), cartItem.getProduct().getDefaultImage().getName(),
                        cartItem.getProduct().getDefaultImage().getType()));
                for (CartItemAttribute cartItemAttribute : cartItem.getCartItemAttributes()) {
                    Attribute attribute = attributeDao.find(cartItemAttribute.getAttributeId());
                    cartItemDetail.getAttributeNameValues().add(new AttributeNameValue(attribute.getId(),attribute.getOption().getName().toString(),
                            attribute.getOptionValue().getValue()));
                }
                cartItemDetails.add(cartItemDetail);
            }
        }
        cartDetail.setCart(cartItemDetails);
        return cartDetail;
    }


    @Override
    public void updateCartItem(CartItemDto cartItemDto) throws Exception {
        CartItem cartItem = cartItemDao.find(cartItemDto.getCartItemId());
        if (cartItem == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItemDao.update(cartItem);
    }

    private CartItem hasProduct(Set<CartItem> cartItems, Product product) {
        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct() == product)
                    return cartItem;
            }
        }
        return null;
    }


}

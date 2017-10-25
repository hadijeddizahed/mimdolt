package ir.mimdolt.store.persist.entity.cartitem;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.cart.Cart;
import ir.mimdolt.store.persist.entity.cartitemattribute.CartItemAttribute;
import ir.mimdolt.store.persist.entity.product.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 1/21/2017.
 */

@Entity
@Table(name = "cart_item")
public class CartItem extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5995234739803328795L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "cartId")
    @JsonManagedReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonManagedReference
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "cartItem")
    private Set<CartItemAttribute> cartItemAttributes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<CartItemAttribute> getCartItemAttributes() {
        return cartItemAttributes;
    }

    public void setCartItemAttributes(Set<CartItemAttribute> cartItemAttributes) {
        this.cartItemAttributes = cartItemAttributes;
    }
}

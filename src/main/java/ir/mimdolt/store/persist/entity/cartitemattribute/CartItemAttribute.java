package ir.mimdolt.store.persist.entity.cartitemattribute;

import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.cartitem.CartItem;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hadi Jeddi Zahed on 2/19/2017.
 */

@Entity
@Table(name = "cart_item_attribute")
public class CartItemAttribute extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2106902344939798834L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="ATTRIBUTE_ID", nullable=false)
    private Long attributeId;

    @ManyToOne()
    @JoinColumn(name = "cart_item_id", nullable = false)
    private CartItem cartItem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}

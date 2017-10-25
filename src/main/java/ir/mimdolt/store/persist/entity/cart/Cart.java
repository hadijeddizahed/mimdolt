package ir.mimdolt.store.persist.entity.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ir.mimdolt.store.persist.entity.BaseEntity;
import ir.mimdolt.store.persist.entity.cartitem.CartItem;
import ir.mimdolt.store.persist.entity.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hadi Jeddi Zahed on 1/21/2017.
 */

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -68765266043129022L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    @JsonBackReference
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "total_cost")
    private double totalCost;

    public Cart() {
    }

    public Cart(User user, Set<CartItem> cartItems, double totalPrice) {
        this.user = user;
        this.cartItems = cartItems;
        this.totalCost = totalPrice;
    }

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

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

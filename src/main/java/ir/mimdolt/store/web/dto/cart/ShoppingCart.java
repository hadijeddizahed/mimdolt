package ir.mimdolt.store.web.dto.cart;

import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Hadi Jeddi Zahed on 1/26/2017.
 */
@Component
@Scope(value = "session")
public class ShoppingCart {

    private String productName;
    private int quantity;
    private BigDecimal price;
    private String code;

    public ShoppingCart() {
    }

    public ShoppingCart(String productName, int quantity, BigDecimal price, String code) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.code = code;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

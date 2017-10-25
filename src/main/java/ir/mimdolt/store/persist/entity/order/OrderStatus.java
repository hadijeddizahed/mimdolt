package ir.mimdolt.store.persist.entity.order;

/**
 * Created by Hadi Jeddi Zahed on 2/6/2017.
 */
public enum  OrderStatus {

    ORDERED("ordered"),
    PROCESSED("processed"),
    DELIVERED("delivered"),
    CANCEL("cancel"),
    ;

    private String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

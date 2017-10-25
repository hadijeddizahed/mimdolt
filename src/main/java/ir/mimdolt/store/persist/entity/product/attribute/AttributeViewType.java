package ir.mimdolt.store.persist.entity.product.attribute;

/**
 * Created by Hadi Jeddi Zahed on 4/26/2017.
 */
public enum AttributeViewType {
    PRODUCT_PAGE(0),FILTER_PANEL(1),BOTH(2);
    private int value;

    AttributeViewType(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

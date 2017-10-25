package ir.mimdolt.store.web.dto.productimage;

/**
 * Created by Hadi Jeddi Zahed on 2/14/2017.
 */
public enum ImageSize {

    LARGE("LARGE"),
    MEDIUM("MEDIUM"),
    SMALL("SMALL");

    private final String value;

    ImageSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

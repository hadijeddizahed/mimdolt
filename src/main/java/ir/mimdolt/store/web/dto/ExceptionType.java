package ir.mimdolt.store.web.dto;

/**
 * Created by Hadi Jeddi Zahed on 11/12/2016.
 */
public enum  ExceptionType {
    NOTFOUND("NOTFOUND"),DUPLICATE("DUPLICATE"),UNKNOWN("UNKNOWN"), INVALID("INVALID");

    private String value;


    ExceptionType(String value) {
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

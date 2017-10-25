package ir.mimdolt.store.model;

/**
 * Created by Hadi Jeddi Zahed on 11/12/2016.
 */
public class BusinessException extends Exception {

    private String type;
    private String className;

    public BusinessException(String type, String className) {
        this.type = type;
        this.className = className;
    }

    public BusinessException(String message, String type, String className) {
        super(message);
        this.type = type;
        this.className = className;
    }

    public BusinessException(String message, Throwable cause, String type, String className) {
        super(message, cause);
        this.type = type;
        this.className = className;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}

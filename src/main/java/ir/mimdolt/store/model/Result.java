package ir.mimdolt.store.model;

/**
 * Created by Hadi Jeddi Zahed on 11/10/2016.
 */
public class Result {

    private boolean success;
    private String error;

    public Result(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

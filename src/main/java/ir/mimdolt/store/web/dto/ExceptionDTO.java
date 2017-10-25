package ir.mimdolt.store.web.dto;

/**
 * Created by Hadi Jeddi Zahed on 11/12/2016.
 */
public class ExceptionDTO {
    private String errCode;
    private String errMsg;
    private String stackTrace;

    public ExceptionDTO(String errCode, String errMsg, String stackTrace) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.stackTrace = stackTrace;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}

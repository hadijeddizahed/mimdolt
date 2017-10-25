package ir.mimdolt.store.web.dto.user;

/**
 * Created by Hadi Jeddi Zahed on 6/17/2017.
 */
public class ChangePasswordDto {

    private String oldPass;
    private String newPass;
    private String rePass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRePass() {
        return rePass;
    }

    public void setRePass(String rePass) {
        this.rePass = rePass;
    }
}

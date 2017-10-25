package ir.mimdolt.store.business.captcha;

import ir.mimdolt.store.model.BusinessException;

/**
 * Created by Hadi Jeddi Zahed on 6/16/2017.
 */
public interface RecaptchaService {

    boolean isResponseValid(String remoteIp, String response) throws BusinessException;

}

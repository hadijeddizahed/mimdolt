package ir.mimdolt.store.business.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.web.dto.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * Created by Hadi Jeddi Zahed on 6/16/2017.
 */
@Service
public class RecaptchaServiceImpl implements RecaptchaService {

    private static class RecaptchaResponse {
        @JsonProperty("success")
        private boolean success;
        @JsonProperty("error-codes")
        private Collection<String> errorCodes;
    }

    private final RestTemplate restTemplate;


    private String recaptchaUrl = "https://www.google.com/recaptcha/api/siteverify";


    private String recaptchaSecretKey = "6Lc6riUUAAAAAI7qGzBTlga9TvJTzXiQN52ui4Kp";

    @Autowired
    public RecaptchaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isResponseValid(String remoteIp, String response) throws BusinessException {
        RecaptchaResponse recaptchaResponse;
        try {
            recaptchaResponse = restTemplate.postForEntity(
                    recaptchaUrl, createBody(recaptchaSecretKey, remoteIp, response), RecaptchaResponse.class)
                    .getBody();
        } catch (RestClientException e) {
            throw new BusinessException("Captcha is invalid", ExceptionType.INVALID.getValue());
        }
        return recaptchaResponse.success;
    }

    private MultiValueMap<String, String> createBody(String secret, String remoteIp, String response) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secret);
        form.add("remoteip", remoteIp);
        form.add("response", response);
        return form;
    }

}
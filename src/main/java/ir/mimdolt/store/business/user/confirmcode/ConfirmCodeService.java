package ir.mimdolt.store.business.user.confirmcode;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.user.confirmcode.ConfirmCode;
import org.springframework.stereotype.Component;

/**
 * Created by Hadi Jeddi Zahed on 5/4/2017.
 */
@Component
public interface ConfirmCodeService extends AbstractService<ConfirmCode,ConfirmCode,Long> {

    void send(String username) throws Exception;

    boolean confirm(String username, String code) throws Exception;
}

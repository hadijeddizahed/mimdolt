package ir.mimdolt.store.persist.entity.user.confirmcode;

import ir.mimdolt.store.persist.AbstractDAO;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 5/4/2017.
 */
public interface ConfirmCodeDao extends AbstractDAO<ConfirmCode, Long> {

    ConfirmCode find(String username) throws Exception;

    List<ConfirmCode> list(String username) throws Exception;
}

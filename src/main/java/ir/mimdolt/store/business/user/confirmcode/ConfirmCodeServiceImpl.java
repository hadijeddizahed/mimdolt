package ir.mimdolt.store.business.user.confirmcode;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.persist.entity.user.confirmcode.ConfirmCode;
import ir.mimdolt.store.persist.entity.user.confirmcode.ConfirmCodeDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Hadi Jeddi Zahed on 5/4/2017.
 */

@Service
@Transactional
public class ConfirmCodeServiceImpl extends AbstractServiceImpl<ConfirmCode, ConfirmCode, Long> implements ConfirmCodeService {

    @Autowired
    private ConfirmCodeDao confirmCodeDao;

    @Override
    public void send(String username) throws Exception {
        ConfirmCode confirmCode = new ConfirmCode();
        String code = RandomStringUtils.randomNumeric(5);
        confirmCode.setUsername(username);
        confirmCode.setCode(code);
        confirmCode.setStatus(Boolean.TRUE);
        if (confirmCodeDao.list(username).isEmpty())
            confirmCodeDao.add(confirmCode);
    }

    @Override
    public boolean confirm(String username, String code) throws Exception {
        ConfirmCode confirmCode = confirmCodeDao.find(username);
        if (!confirmCode.getCode().equals(code))
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(),this.getEntityClassName());
        return true;
    }
}

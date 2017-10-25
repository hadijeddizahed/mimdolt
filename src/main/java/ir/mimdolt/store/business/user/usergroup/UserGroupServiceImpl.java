package ir.mimdolt.store.business.user.usergroup;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.persist.entity.user.usergruop.UserGroup;
import ir.mimdolt.store.web.dto.user.UserDto;
import ir.mimdolt.store.web.dto.user.UserGroupDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/24/2017.
 */

@Service
@Transactional(rollbackOn = Exception.class)
public class UserGroupServiceImpl extends AbstractServiceImpl<UserGroupDto,Long,UserGroup> implements UserGroupService{
    @Override
    public UserGroupDto find(Long id) throws Exception {
        return null;
    }

    @Override
    public UserGroupDto find(String username) throws Exception {
        return null;
    }

    @Override
    public UserGroupDto save(UserGroupDto userGroupDto) throws Exception {
        return null;
    }

    @Override
    public UserGroupDto update(UserGroupDto userGroupDto) throws Exception {
        return null;
    }

    @Override
    public void saveOrUpdate(UserGroupDto userGroupDto) throws Exception {

    }

    @Override
    public List<UserDto> listAll() throws Exception {
        return null;
    }
}

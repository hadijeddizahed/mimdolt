package ir.mimdolt.store.business.user.usergroup;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.persist.entity.user.usergruop.UserGroup;
import ir.mimdolt.store.web.dto.user.UserDto;
import ir.mimdolt.store.web.dto.user.UserGroupDto;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 2/24/2017.
 */
public interface UserGroupService extends AbstractService<UserGroupDto, Long, UserGroup> {

    UserGroupDto find(Long id) throws Exception;

    UserGroupDto find(String username) throws Exception;


    UserGroupDto save(UserGroupDto userGroupDto) throws Exception;

    UserGroupDto update(UserGroupDto userGroupDto) throws Exception;

    void saveOrUpdate(UserGroupDto userGroupDto) throws Exception;

    List<UserDto> listAll() throws Exception;
}

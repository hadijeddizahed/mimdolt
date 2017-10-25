package ir.mimdolt.store.business.user;

import ir.mimdolt.store.business.AbstractService;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.model.Response;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.web.dto.user.ChangePasswordDto;
import ir.mimdolt.store.web.dto.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */

@Component
public interface UserService extends AbstractService<UserDto,User,Long> {

    UserDto find(Long id) throws Exception;

    UserDto find(String username) throws Exception;

    void changePassword(ChangePasswordDto changePasswordDto)throws Exception;

    UserDto save(UserDto userDto) throws Exception;

    UserDto update(UserDto userDto) throws Exception;

    void saveOrUpdate(UserDto user) throws Exception;

    Response updateProfile(UserDto userDto) throws Exception;

    PagingData<UserDto> paging(Integer offset, Integer maxResult) throws Exception;

    List<UserDto> listAll()throws Exception;

    User getLoggedInUser() throws Exception;


}

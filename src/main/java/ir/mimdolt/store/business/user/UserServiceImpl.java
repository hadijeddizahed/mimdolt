package ir.mimdolt.store.business.user;

import ir.mimdolt.store.business.AbstractServiceImpl;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.model.Response;
import ir.mimdolt.store.persist.entity.role.Role;
import ir.mimdolt.store.persist.entity.role.RoleDao;
import ir.mimdolt.store.persist.entity.user.User;
import ir.mimdolt.store.persist.entity.user.UserDao;
import ir.mimdolt.store.persist.entity.user.confirmcode.ConfirmCodeDao;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.user.ChangePasswordDto;
import ir.mimdolt.store.web.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<UserDto, User, Long> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ConfirmCodeDao confirmCodeDao;


    @Override
    public UserDto find(Long id) throws Exception {
        User user = userDao.find(id);
        if (user == null)
            throw new BusinessException(ExceptionType.NOTFOUND.getValue(), this.getEntityClassName());
        UserDto userDto = new UserDto();
        return userDto.map(user);
    }

    @Override
    public UserDto find(String username) throws Exception {
        return new UserDto().map(userDao.find(username));
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) throws Exception {
        User currentUser = getLoggedInUser();
        if (!passwordEncoder.matches(changePasswordDto.getOldPass(), currentUser.getPassword())){
            throw new BusinessException(ExceptionType.INVALID.getValue(),this.getEntityClassName());
        }

        currentUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPass()));
        userDao.update(currentUser);
    }

    @Override
    public UserDto save(UserDto userDto) throws Exception {
        User user = new User();

//        user.map(userDto);
        user.setUsername(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.getRoles().add(roleDao.find("user"));
        userDao.saveOrUpdate(user);

        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) throws Exception {
        return null;
    }

    @Override
    public void saveOrUpdate(UserDto userDto) throws Exception {
        User user = null;
        if (userDto.getId() == null) {
            user = new User();
            user.map(userDto);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            for (String role : userDto.getRoles()) {
                Role role1 = roleDao.find(role);
                user.getRoles().add(role1);
            }
            userDao.saveOrUpdate(user);
        } else {
            user = userDao.find(userDto.getId());
            user.map(userDto);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDao.update(user);
        }
    }

    @Override
    public Response updateProfile(UserDto userDto) throws Exception {
        User currentUser = this.getLoggedInUser();
        currentUser.map(userDto);
        userDao.update(currentUser);
        return new Response(200,"OK",null);
    }

    @Override
    public PagingData<UserDto> paging(Integer offset, Integer maxResult) throws Exception {
        PagingData<User> userPagingData = userDao.paging(offset, maxResult);
        PagingData<UserDto> pagingData = new PagingData<>();
        List<UserDto> userDtoList = new ArrayList<>();
        userPagingData.getData().forEach(user -> {
            try {
                userDtoList.add(new UserDto().map(user));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pagingData.setData(userDtoList);
        pagingData.setCount(userPagingData.getCount());
        return pagingData;
    }

    @Override
    public List<UserDto> listAll() throws Exception {
        List<User> users = userDao.getAll();
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            list.add(new UserDto().map(user));
        }
        return list;
    }

    @Override
    public User getLoggedInUser() throws Exception {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }

        return userDao.find(userName);
    }

}

package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.business.user.address.AddressService;
import ir.mimdolt.store.model.PagingData;
import ir.mimdolt.store.model.Response;
import ir.mimdolt.store.persist.entity.user.address.Address;
import ir.mimdolt.store.web.dto.user.ChangePasswordDto;
import ir.mimdolt.store.web.dto.user.UserDto;
import ir.mimdolt.store.web.dto.user.address.AddressDto;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Users management API")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public
    @ResponseBody
    List<UserDto> usersList() throws Exception {
        logger.debug("get users list");
        return userService.listAll();
    }

    @RequestMapping(value = "/admin/users/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    UserDto getUser(@PathVariable Long userId) throws Exception {
        return userService.find(userId);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public
    @ResponseBody
    void saveUser(@RequestBody UserDto user) throws Exception {
        userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/admin/users/page/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    PagingData<UserDto> paging(@PathVariable("page") int page) throws Exception {
        return userService.paging(page, 4);
    }


    @RequestMapping(value = "/user/address/add", method = RequestMethod.POST)
    public
    @ResponseBody
    void addAddress(@RequestBody AddressDto addressDto) throws Exception {
        addressService.saveOrUpdated(addressDto);
    }

    @RequestMapping(value = "/user/address/remove/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Response removeAddress(@PathVariable(value = "id") Long id) throws Exception {
        return addressService.remove(id);
    }


    @RequestMapping(value = "/user/address/list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Address> listAddress() throws Exception {
        return addressService.list();
    }

    @RequestMapping(value = "/user/address/setDefault/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    void setDefaultAddress(@PathVariable("id") Long id) throws Exception {
        addressService.setDefaultAddress(id);
    }

    @RequestMapping(value = "/user/address/default", method = RequestMethod.GET)
    public
    @ResponseBody
    AddressDto getDefaultAddress() throws Exception {
        return addressService.getDefaultAddress();
    }

    @RequestMapping(value = "/user/changePass", method = RequestMethod.POST)
    public
    @ResponseBody
    void changePassword(@RequestBody(required = true) ChangePasswordDto changePass) throws Exception {
        userService.changePassword(changePass);
    }

    @RequestMapping(value = "/user/updateProfile", method = RequestMethod.POST)
    public
    @ResponseBody
    Response updateProfile(@RequestBody(required = true) UserDto userDto) throws Exception {
        return userService.updateProfile(userDto);
    }
}

 

package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.captcha.RecaptchaService;
import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.business.user.confirmcode.ConfirmCodeService;
import ir.mimdolt.store.model.BusinessException;
import ir.mimdolt.store.web.dto.ExceptionType;
import ir.mimdolt.store.web.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hadi Jeddi Zahed on 1/4/2017.
 */

@RestController
public class PublicController {

//    @Autowired
//    private ServiceFaced serviceFaced;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmCodeService confirmCodeService;

    @Autowired
    private RecaptchaService recaptchaService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    UserDto addUser(@RequestBody UserDto userDto,HttpServletRequest request) throws Exception {
        boolean isValid = recaptchaService.isResponseValid(request.getRemoteAddr(),userDto.getgRecaptchaResponse());
        if (!isValid)
            throw new BusinessException("Captcha is invalid", ExceptionType.INVALID.getValue());
        return userService.save(userDto);
    }

    @RequestMapping(value = "/sendConfirmCode", method = RequestMethod.POST)
    public
    @ResponseBody
    void sendCode(@RequestParam(name = "username")String username) throws Exception {
        confirmCodeService.send(username);
    }


    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean confirm(@RequestParam(name = "username")String username,@RequestParam(name = "code")String code) throws Exception {
        return confirmCodeService.confirm(username,code);
    }
}

package ir.mimdolt.store.web.controller;

import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.persist.entity.Token;
import ir.mimdolt.store.security.SecurityUtils;
import ir.mimdolt.store.web.dto.user.UserDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "Users management API")
public class SeurityController {


    @Autowired
    private UserService userRepo;


//    @Autowired
//    private TokenRepo tokenRepo;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    UserDto getUserAccount() throws Exception {
        UserDto user = userRepo.find(SecurityUtils.getCurrentLogin());
        user.setPassword(null);
        return user;
    }


    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/security/tokens", method = RequestMethod.GET)
    public @ResponseBody
    List<Token> getTokens () {
////        List<Token> tokens = tokenRepo.findAll();
//        for(Token t:tokens) {
//            t.setSeries(null);
//            t.setValue(null);
//        }
        return null;
    }



}

package ir.mimdolt.store.security;

import ir.mimdolt.store.business.user.UserService;
import ir.mimdolt.store.web.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        UserDto user = null;
        try {
            user = userService.find(authentication.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
    }
}

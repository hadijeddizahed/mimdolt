package ir.mimdolt.store.config;


import ir.mimdolt.store.security.RestUnauthorizedEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.DelegatingLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;

import java.util.Collections;
import java.util.LinkedHashMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"ir.mimdolt.store.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public static final String REMEMBER_ME_KEY = "rememberme_key";

    public SecurityConfig() {
        super();
        logger.info("loading SecurityConfig ................................................ ");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private AuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler restAuthenticationFailureHandler;

//    @Autowired
//    private RememberMeServices rememberMeServices;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html", "/shopping-cart",
                "/partials/**", "/mimdolt/**", "/template/**", "/", "/product/**", "/error/**", "/store/**","/sendConfirmCode","/confirm","/register", "/back/login", "/api/display/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/logout").permitAll()
                .antMatchers("/api/admin/**").hasAnyAuthority("admin")
//                .antMatchers("/shopping-cart/admin/**").hasAnyAuthority("user")
                .antMatchers("/sendConfirmCode", "/confirm").permitAll()
                .antMatchers("/api/product/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/api/category/**").permitAll()
                .antMatchers("/api/cart/**").permitAll()
                .antMatchers("/api/display/**").permitAll()
                .antMatchers("/store/category/**").permitAll()
                .antMatchers("/shopping-cart/chooseAddress").hasAnyAuthority("user,admin")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticate")
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and();
//            .rememberMe()
//                .rememberMeServices(rememberMeServices)
//                .key(REMEMBER_ME_KEY)
//                .and();
    }

    @Bean
    public DelegatingLogoutSuccessHandler logoutSuccessHandler() {
        LinkedHashMap<RequestMatcher, LogoutSuccessHandler> matcherToHandler = new LinkedHashMap<RequestMatcher, LogoutSuccessHandler>();
        matcherToHandler.put(new MediaTypeRequestMatcher(new ParameterContentNegotiationStrategy(Collections.singletonMap("json", MediaType.APPLICATION_JSON)), MediaType.APPLICATION_JSON), new HttpStatusReturningLogoutSuccessHandler(HttpStatus.FOUND));
        DelegatingLogoutSuccessHandler delegatingLogoutSuccessHandler = new DelegatingLogoutSuccessHandler(matcherToHandler);
        delegatingLogoutSuccessHandler.setDefaultLogoutSuccessHandler(new SimpleUrlLogoutSuccessHandler());

        return delegatingLogoutSuccessHandler;
    }

}

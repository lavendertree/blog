package blog.config.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by weber on 2017/5/28.
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses =UserServiceDetails.class )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceDetails userServiceDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/me/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureHandler(new AjaxLoginFailureHandler())
                .successHandler(new AjaxLoginSuccessHandler())
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login.html")
                .and().rememberMe().alwaysRemember(true)
                .and().headers().frameOptions().sameOrigin();

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetails).passwordEncoder(new StandardPasswordEncoder());
    }

}
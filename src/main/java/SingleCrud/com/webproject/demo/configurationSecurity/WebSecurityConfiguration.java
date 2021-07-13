package SingleCrud.com.webproject.demo.configurationSecurity;

import SingleCrud.com.webproject.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public WebSecurityConfiguration(BCryptPasswordEncoder passwordEncoder, MyUserDetailsService myUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("oke");
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/", "/login", "/dangky").permitAll();
        httpSecurity.authorizeRequests().and().formLogin()
                    .loginProcessingUrl("/j_spring_security_check")
                    .loginPage("/login")
                    .defaultSuccessUrl("/UserPage")
                    .failureUrl("/login?wrongAcc=true")
                    .usernameParameter("username")
                    .passwordParameter("password");
    }
}

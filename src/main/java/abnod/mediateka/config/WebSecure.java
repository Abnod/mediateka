package abnod.mediateka.config;

import abnod.mediateka.util.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecure extends WebSecurityConfigurerAdapter {


    private final UserDetailService userDetailService;

    @Autowired
    public WebSecure(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/media/search").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/media/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/media/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/media/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/", true).permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}

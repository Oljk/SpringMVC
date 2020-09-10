package springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
 @EnableWebSecurity // @EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user")
                .password(passwordEncoder().encode("password")).roles("USER").and().
                withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN");
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     /*  http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
*/
        http.authorizeRequests().antMatchers("/books").access("hasRole('USER')")
                .antMatchers("/add/").hasRole("ADMIN").and().
                formLogin().loginPage("/login").permitAll().and().logout().logoutUrl("/logout");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("                                     VERSION    10                          ");
        System.out.println("----------------------------------------------------------------------------");
    }

}
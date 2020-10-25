package springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springmvc.model.dao.DaoConnection;
import springmvc.model.services.impl.UserDetailsServiceImpl;

@Configuration
 @EnableWebSecurity // @EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DaoConnection dataSource;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
*/

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)/*
                .passwordEncoder(passwordEncoder())*/;
    }
/*
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

 /*   @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password from users where LOGIN=?")
                .authoritiesByUsernameQuery(
                        "select username, isAdmin role from USERS  where LOGIN=?"); //nvl(isAdmin, 'ROLE_ADMIN', 'ROLE_USER')
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     /*  http.authorizeRequests()
                .anyRequest().authenticated()

               .and().httpBasic();
*/
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .defaultSuccessUrl("/books")
                .failureUrl("/login-error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true);
        /*

        http.authorizeRequests()
                .antMatchers("/add/* ").access("hasRole('ADMIN')")
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check").failureUrl("/helloworld")
                .usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .and()
                .logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf(); */

      /*  http.authorizeRequests().antMatchers("/books").access("hasRole('USER')")
                .antMatchers("/add").hasRole("ADMIN").and().
                formLogin().loginPage("/login").permitAll().and().logout().logoutUrl("/logout");*/
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("                                     VERSION    16                          ");
        System.out.println("----------------------------------------------------------------------------");
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}


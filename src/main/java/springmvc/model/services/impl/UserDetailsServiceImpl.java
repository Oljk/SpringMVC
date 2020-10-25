package springmvc.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springmvc.model.Roles;
import springmvc.model.entities.User;
import springmvc.model.services.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getUser(login);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(user.isAdmin()?Roles.ROLE_ADMIN:Roles.ROLE_USER));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    /*    System.out.println(userDetails.toString());
        System.out.println(encoder.encode("123"));
        System.out.println(encoder.encode("123").equals(user.getPassword()));
        System.out.println(encoder.matches("123", userDetails.getPassword()));
        System.out.println(encoder.matches("123", new BCryptPasswordEncoder().encode("123")));*/
        return userDetails;
    }

}

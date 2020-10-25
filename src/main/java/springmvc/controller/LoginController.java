package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.model.Roles;
import springmvc.model.dao.ConfigurationDAO;
import springmvc.model.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.ui.Model;
import springmvc.model.services.UserService;


@Controller
public class LoginController {
    @Autowired
    UserService userService;


    /**
     * methods for tests change!
     * */

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
      /*  SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth0 = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> list = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
             String role = auth.getAuthority();
            System.out.println(role);
        }*/
        return "login";
    }
    @RequestMapping(value="/login-error")
    public String loginError() {
        return "login_error";
    }

  //  @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginPost(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        model.addAttribute("current", "/login.jsp");


        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return "login";
        } else {
            for (GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
                if (Roles.ROLE_ADMIN.equals(auth.getAuthority())) {
                    return "books";
                }
            }
        }
        return "login";
    }

    //@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {

        model.addAttribute("error", "true");
        return "login";
    }

  //  @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "logout";
    }

  /*
  other sh. methods.
   */
    @RequestMapping("/index")
    public String listContacts(Map<String, Object> map) {
        User u = new User();
        map.put("user", u);
        map.put("login", u.getLogin());
        map.put("contactList", (new ConfigurationDAO()).getUserDao().getUserByLogin("User1Sur"));
        return "login1";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
/*
    @RequestMapping(value = "/registration", method =RequestMethod.GET)
public void getRegister(Model model) {

}*/

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerPost(Model model, HttpServletRequest req) {
        User user =  new User();
        user.setEmail(req.getParameter("Email"));
        user.setName(req.getParameter("Name"));
        user.setPhone_number(req.getParameter("Phonenumber"));
        user.setPassword(user.passwordEncoder().encode(req.getParameter("Password")));
        user.setLogin(req.getParameter("Login"));
        userService.addUser(user);
        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerGet(Model model, HttpServletRequest req) {
        return "registration";
    }
}

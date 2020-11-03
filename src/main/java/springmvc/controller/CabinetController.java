package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.model.Roles;
import springmvc.model.entities.User;
import springmvc.model.services.UserService;

@Controller
public class CabinetController {

   @Autowired
   UserService userService;

    @RequestMapping(value="/cabinet")
    public String getCabinet(Model model) {
        for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            String role = auth.getAuthority();
            if (Roles.ROLE_ANONYMOUS.equals(role)) {
                return "redirect:/login";
            } else {
                User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
                model.addAttribute("user", user);
                return "cabinet";
            }
        }
        return "home";
    }
}

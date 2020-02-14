package springmvc.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.model.dao.ConfigurationDAO;
import springmvc.model.entities.*;

import javax.jws.soap.SOAPBinding;
import java.util.Map;

import org.springframework.ui.Model;


@Controller
public class LoginController {

    @RequestMapping("/index")
    public String listContacts(Map<String, Object> map) {
        User u = new User();
        u.setLogin(new Login());
        map.put("user", u);
        map.put("login", u.getLogin());
        map.put("contactList", (new ConfigurationDAO()).getUserDao().getUserByLogin("User1Sur"));
        return "login";
    }

    @RequestMapping(value="/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "login";
    }



    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}

package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StaticResourceController {
    @RequestMapping(value = "/staticResourceTest")
    public String staticResource(Model model) {
        return "staticResourceTest";
    }
}

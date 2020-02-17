package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.model.services.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/{id}/{amount}")
    public String process(Model model,
                          @PathVariable(value = "id") int id,
                          @PathVariable(value = "amount") int amount) {
        orderService.processOrder(id, amount);
        return "redirect:/books";
    }

}

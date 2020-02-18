package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.model.services.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/{id}/{amount}")
    public String process(Model model,
                          @PathVariable(value = "id") int id,
                          @PathVariable(value = "amount") int amount) {
        orderService.processOrder(id, amount);
        return "redirect:/books";
    }

}

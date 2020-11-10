package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.model.dao.OrderDAO;
import springmvc.model.entities.Order;
import springmvc.model.services.CurrentOrderService;
import springmvc.model.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final CurrentOrderService currentOrderService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final OrderDAO orderDAO;

    @Autowired
    public OrderController(CurrentOrderService currentOrderService, OrderService orderService, OrderDAO orderDAO) {
        this.currentOrderService = currentOrderService;
        this.orderService = orderService;
        this.orderDAO = orderDAO;
    }

    @RequestMapping(value = "/current/{id}", method = RequestMethod.GET)
    public @ResponseBody Order read(@PathVariable(value = "id") String orderID) {
        return currentOrderService.read(Integer.valueOf(orderID));
    }

    @RequestMapping("/{id}/{amount}")
    public String process(Model model,
                          @PathVariable(value = "id") int id,
                          @PathVariable(value = "amount") int amount) {
        orderService.processOrder(id, amount);
        return "redirect:/books";
    }


}

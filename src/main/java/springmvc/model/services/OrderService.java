package springmvc.model.services;

import org.springframework.stereotype.Service;
import springmvc.model.entities.Book;
import springmvc.model.entities.Order;

@Service
public interface OrderService {
    Book processOrder(int id, int quantity);
    Order processOrder(Order order);
}

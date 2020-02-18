package springmvc.model.services;

import org.springframework.stereotype.Service;
import springmvc.model.entities.Book;

@Service
public interface OrderService {
    Book processOrder(int id, int quantity);

}

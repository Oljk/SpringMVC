package springmvc.model.services;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void processOrder(int id, int quantity);

}

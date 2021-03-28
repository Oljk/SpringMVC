package springmvc.model.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springmvc.model.entities.Order;


public interface CurrentOrderService {
    Order create(Order curOrd);
    Order read(String sessionId);
    void update(String sessionId, Order order);
    void delete(String sessionId);
}

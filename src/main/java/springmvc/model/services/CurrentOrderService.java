package springmvc.model.services;

import org.springframework.stereotype.Repository;
import springmvc.model.entities.Order;

@Repository
public interface CurrentOrderService {
    Order create(Order curOrd);
    Order read(Integer orderId);
    void update(Integer orderId, Order order);
    void delete(Integer orderId);
}

package springmvc.model.dao;

import springmvc.model.entities.*;

public interface OrderDAO extends DAO { ;
    boolean makeOrderDone(int order_id);
    boolean makeOrderDone(Order order);
    int createOrder(Order order);
}

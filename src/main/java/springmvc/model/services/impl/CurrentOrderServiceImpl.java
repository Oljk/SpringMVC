package springmvc.model.services.impl;

import springmvc.model.entities.Order;
import springmvc.model.services.CurrentOrderService;

import java.util.HashMap;
import java.util.Map;

public class CurrentOrderServiceImpl implements CurrentOrderService {
    private Map<Integer, Order> orderList;

    public CurrentOrderServiceImpl() {
        orderList = new HashMap<>();
    }
/*
* TODO: менить ордер айди на сешн айди для тех заказов, что  в памяти, а не в базе
* */
    @Override
    public Order create(Order curOrd) {
        if (orderList.containsKey(curOrd.getOrder_id())) {
            update(curOrd.getOrder_id(), curOrd);
        }
        orderList.put(curOrd.getOrder_id(), curOrd);
        return null;
    }

    @Override
    public Order read(Integer orderId) {
        if (!orderList.containsKey(orderId)) {
            return null;
        }
        return orderList.get(orderId);
    }

    @Override
    public void update(Integer orderId, Order order) {
        orderList.put(order.getOrder_id(), order);
    }

    @Override
    public void delete(Integer orderId) {
        if (orderList.containsKey(orderId)) {
            orderList.remove(orderId);
        }
    }
}

package springmvc.model.services.impl;

import springmvc.model.entities.Order;
import springmvc.model.services.CurrentOrderService;

import java.util.HashMap;
import java.util.Map;
/*
* корзина по факту
*
*/

public class CurrentOrderServiceImpl implements CurrentOrderService {
    private Map<String, Order> orderList;
// session_id + Order

    public CurrentOrderServiceImpl() {
        orderList = new HashMap<>();
    }

    @Override
    public Order create(Order curOrd) {
        if (orderList.containsKey(curOrd.getSessionId())) {
            update(curOrd.getSessionId(), curOrd);
        }
        orderList.put(curOrd.getSessionId(), curOrd);
        return orderList.get(curOrd.getSessionId());
    }

    @Override
    public Order read(String sessionId) {
        if (!orderList.containsKey(sessionId)) {
            return null;
        }
        return orderList.get(sessionId);
    }

    @Override
    public void update(String sessionId, Order order) {
        orderList.put(order.getSessionId(), order);
    }

    @Override
    public void delete(String sessionId) {
        if (orderList.containsKey(sessionId)) {
            orderList.remove(sessionId);
        }
    }
}

package springmvc.model.dao.daoImpl;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.model.dao.*;
import springmvc.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDAOImpl implements OrderDAO {
    @Autowired
    CommonDAO commonDAO;

    @Autowired
    BookDAO bookDAO;

    private final DaoConnection Dao = DaoConnection.getInstance();

    private final String INSERT_ORDER = "insert into ORDERS (ORDER_ID, USER_ID, ADRESS, SUMM, COMMENTS, ISDONE) values (?,?,?,?,?,?)";
    private final String INSERT_ORDER_ITEM = "insert into ORDER_ITEM (ORDER_ID, ITEM_ID, AMOUNT) VALUES (?,?,?)";
    private final String UPDATE_ORDER_DONE = "update ORDERS set ISDONE = ? where ORDER_ID = ?";
    private final String GET_OBJECT_BY_ID = "select ORDER_ID, user_id, adress, summ, comments, ISDONE from ORDERS\n" +
            "    where ORDER_ID = ?";
    private final String GET_ORDER_ITEMS_BYORID = "select ORDER_ID, ITEM_ID, AMOUNT from ORDER_ITEM where ORDER_ID = ?";

    @Override
    public int createOrder(Order order) {
        int orderId = commonDAO.getId();
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, order.getUser_id());
            preparedStatement.setString(3, order.getAdress());
            preparedStatement.setDouble(4, order.getSumm());
            preparedStatement.setString(5, order.getComments());
            preparedStatement.setInt(6,order.isDone()?1:0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (OrderItem item: order.getOrderItems().values()) {
            createOrderItem(item, orderId);
        }
        return orderId;
    }

    private void createOrderItem(OrderItem orderItem, int order_id) {
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_ITEM)) {
            preparedStatement.setInt(1, order_id);
            preparedStatement.setInt(2, orderItem.orderItemId());
            preparedStatement.setInt(6, orderItem.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObjectById(int id) {
        Order order = null;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OBJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order = parseOrder(resultSet);
            order.addOrderItems(getOrderItemsByOrId(order.getOrder_id()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return order;
    }

    @Override
    public boolean makeOrderDone(int order_id) {
        return updateOrderDone(order_id, true);
    }

    @Override
    public boolean makeOrderDone(Order order) {
        order.setDone(true);
        return updateOrderDone(order.getOrder_id(), true);
    }

    private boolean updateOrderDone(int order_id, boolean isDone) {
        boolean ok = false;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_DONE)) {
            preparedStatement.setInt(2, order_id);
            preparedStatement.setInt(1, isDone?1:0);
            ok = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    public List<OrderItem> getOrderItemsByOrId(int orderID) {
        List<OrderItem> items = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_ITEMS_BYORID)) {
            preparedStatement.setInt(1, orderID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                items.add(parseOrderItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return items;
    }

    private OrderItem parseOrderItem(ResultSet resultSet) {
        OrderItem item = null;
        try {
            int orderId = resultSet.getInt("ORDER_ID");;
            int item_id = resultSet.getInt("ITEM_ID");
            int amount = resultSet.getInt("AMOUNT");
            item = new OrderItem(orderId, (Book) bookDAO.getObjectById(item_id), amount) ;//int orderId, Book book, int amount
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return item;
    }

    private Order parseOrder(ResultSet resultSet) {
        Order order = null;
        try {
            int orderId = resultSet.getInt("ORDER_ID");
            String address = resultSet.getString("ADRESS");
            int userId = resultSet.getInt("USER_ID");
            double summ = resultSet.getDouble("SUMM");
            int isDone = resultSet.getInt("ISDONE");
            String comments = resultSet.getString("COMMENTS");
            order = new Order(orderId, userId, address, summ, comments, isDone==1) ;
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return order;
    }

}

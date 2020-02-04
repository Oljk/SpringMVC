package springmvc.model.dao.daoImpl;

import org.springframework.stereotype.Service;
import springmvc.model.dao.*;
import springmvc.model.entities.*;


public class OrderDAOImpl implements OrderDAO {

    @Override
    public void connection() {

    }

    @Override
    public void disconnection() {

    }

    @Override
    public Object getObjectById(int id) {
        return null;
    }

    @Override
    public boolean addBook(int order_id, int book_id) {
        return false;
    }

    @Override
    public boolean deleteBook(int order_id, int book_id) {
        return false;
    }

    @Override
    public boolean makeOrderDone(int order_id) {
        return false;
    }

    @Override
    public boolean addBook(Order order, Book book) {
        return false;
    }

    @Override
    public boolean deleteBook(Order order, Book book) {
        return false;
    }

    @Override
    public boolean makeOrderDone(Order order) {
        return false;
    }
}

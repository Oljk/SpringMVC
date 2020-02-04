package springmvc.model.dao;

import springmvc.model.entities.*;

public interface OrderDAO extends DAO {
    boolean addBook(int order_id, int book_id);
    boolean deleteBook(int order_id, int book_id);
    boolean makeOrderDone(int order_id);
    boolean addBook(Order order, Book book);
    boolean deleteBook(Order order, Book book);
    boolean makeOrderDone(Order order);
}

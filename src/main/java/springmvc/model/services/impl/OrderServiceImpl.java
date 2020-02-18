package springmvc.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.model.dao.BookDAO;
import springmvc.model.entities.Book;
import springmvc.model.services.OrderService;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book processOrder(int id, int quantity) {
        Book BookById = (Book) bookDAO.getObjectById(id);
        if(BookById.getAmount() - quantity <  0 ){
            throw new IllegalArgumentException("Out of Stock. Available Units in stock" + BookById.getAmount());
        }
        BookById.setAmount(BookById.getAmount() - quantity);
        bookDAO.updateAmount(id,BookById.getAmount());
        return BookById;
    }

}

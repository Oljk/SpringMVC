package springmvc.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.model.dao.BookDAO;
import springmvc.model.entities.Book;
import springmvc.model.services.OrderService;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public void processOrder(int id, int quantity) {
        Book bookbyId = (Book) bookDAO.getObjectById(id);
        if(bookbyId.getAmount() - quantity <  0 ){
            throw new IllegalArgumentException("Out of Stock. Available Units in stock" + bookbyId.getAmount());
        }
        bookbyId.setAmount(bookbyId.getAmount() - quantity);
        System.out.println(bookDAO.updateAmount(id,bookbyId.getAmount()) + "\n---------------------------------------------------------");
    }

}

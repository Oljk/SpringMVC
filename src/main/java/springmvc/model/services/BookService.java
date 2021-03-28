package springmvc.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.model.OwnException;
import springmvc.model.dao.BookDAO;
import springmvc.model.dao.ItemDAO;
import springmvc.model.entities.Book;
import springmvc.model.entities.Item;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDAO bookDAO;
    @Autowired
    ItemDAO itemDAO;

    public boolean addBook(Book book) throws OwnException {
        if (book.getAmount() < 0) {
            throw new OwnException("invlid amount less than 0");
        }
        return bookDAO.addBook(book);
    }

    public List<Item> getThemes() {
        return itemDAO.getThemes();
    }

    public Book getBookById (int id) {
        System.out.println("book - getBookById: " + id);
        return (Book)bookDAO.getObjectById(id);
    }
}

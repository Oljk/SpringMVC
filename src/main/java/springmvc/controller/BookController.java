package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.model.dao.BookDAO;
import springmvc.model.entities.Book;
import springmvc.model.entities.Item;

@Controller
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping("/books")
    public String list(Model model) {
        // Book book = new Book(new Item(1, 2 , "meitemname", 1, "desc" ), 1245, 12.0, 12, "pub_house");  //(Item item, int year, double price, int amount, String publishing_house)
        model.addAttribute("books",bookDAO.getAllBooks());
        return "books";
    }

}

package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.model.entities.Book;
import springmvc.model.entities.Item;

@Controller
public class BookController {

    @RequestMapping("/books")
    public String list(Model model) {
        Book book = new Book(new Item(1, 2 , "meitemname", 1, "desc" ), 1245, 12.0, 12, "pub_house");  //(Item item, int year, double price, int amount, String publishing_house)
        model.addAttribute("book", book);
        return "books";
    }

}

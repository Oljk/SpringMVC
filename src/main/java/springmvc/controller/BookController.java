package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springmvc.model.OwnException;
import springmvc.model.Roles;
import springmvc.model.dao.BookDAO;
import springmvc.model.entities.Book;
import springmvc.model.entities.ItemType;
import springmvc.model.services.BookService;
@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookController {

    private final BookDAO bookDAO;
    private final BookService bookService;

    @Autowired
    public BookController(BookDAO bookDAO, BookService bookService) {
        this.bookDAO = bookDAO;
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String list(Model model) {
        // Book book = new Book(new Item(1, 2 , "meitemname", 1, "desc" ), 1245, 12.0, 12, "pub_house");  //(Item item, int year, double price, int amount, String publishing_house)
        model.addAttribute("books",bookDAO.getAllBooks());
        return "books";
    }
/*
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("newbook", book);
     //   model.addAttribute("authamount", new EntityWrapper(1));
     //   model.addAttribute("themes", bookService.getThemes());
        return "addBookv2";
    }

    @RequestMapping(value = "/add ", method = RequestMethod.POST)
    public String processAddAuthorToForm(@ModelAttribute("newbook") Book newBook) {
      /*  if ("addBookSbmit".equals(param)) {
            bookService.addBook(newBook);
            return "redirect:/books";
        }else if ("addAuthorSbmit".equals(param)) { */
         //   model.addAttribute("authamount", authamount.getIntValue());
          // model.addAttribute("newbook", newBook);
        //    model.addAttribute("themes", bookService.getThemes());
         /*   bookService.addBook(newBook);
            return "redirect:/books";
       }
        return "books";
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewBookForm(Model model) {
        for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (Roles.ROLE_ADMIN.equals(auth.getAuthority())) {
                Book newBook = new Book();
                model.addAttribute("newbook", newBook);
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                return "addBookv2";
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewBookForm(@ModelAttribute("newbook")  Book newBook) {
        for(GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (Roles.ROLE_ADMIN.equals(auth.getAuthority())) {
                if (newBook == null || newBook.isEmpty()) {
                    return "addBookv2";
                } else {
                    newBook.getItem().setType(ItemType.BOOK);
                    try {
                        bookService.addBook(newBook);
                    } catch (OwnException e) {
                        e.printStackTrace();
                    }
                }
                return "redirect:/books";
            }
        }
        return "redirect:/";
    }

    /**
     * adding binder
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields();
    }



/*

    @RequestMapping(value = "/add", params = {"addsbmbutton={addBookSbmit}"},   method = {RequestMethod.POST})
    public String processAddBook(@ModelAttribute("newbook") Book newBook) {
            bookService.addBook(newBook);
       return "redirect:/books";
    }
*/

}

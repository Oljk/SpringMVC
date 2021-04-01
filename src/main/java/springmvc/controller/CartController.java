package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springmvc.model.OwnException;
import springmvc.model.dao.BookDAO;
import springmvc.model.entities.Book;
import springmvc.model.entities.Item;
import springmvc.model.entities.Order;
import springmvc.model.entities.OrderItem;
import springmvc.model.services.BookService;
import springmvc.model.services.CurrentOrderService;
import springmvc.model.services.OrderService;

import javax.servlet.http.HttpServletRequest;

/**
 * пока корзина тут, возможно перенесется в OrderController
 */

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CurrentOrderService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(method = RequestMethod.POST)//@PostMapping
    public @ResponseBody
    Order create(@RequestBody Order cart) {
        System.out.println("CartController.create() " + cart);
        return cartService.create(cart);
    }

    @RequestMapping(value = "/{session_id}", method = RequestMethod.GET)
    public @ResponseBody Order read(@PathVariable(value = "session_id") String
                                           cartId) {
        return cartService.read(cartId);
    }

    @RequestMapping(value = "/{session_id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "session_id") String cartId,
                       @RequestBody Order cart) {
        cartService.update(cartId, cart);
    }

    @RequestMapping(value = "/{session_id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "session_id") String cartId) {
        cartService.delete(cartId);
    }


    @RequestMapping(value = "/add/{book_id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable String book_id, HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();
        Order cart = cartService.read(sessionId);
        if(cart == null) {
            cart = cartService.create(new Order(sessionId));
        }
        Book product = (Book) bookDAO.getObjectById(Integer.parseInt(book_id));
        if(product == null) {
            throw new IllegalArgumentException(new OwnException("no product by id: " + book_id));
        }
        cart.addOrderItem(new OrderItem(product));
        cartService.update(sessionId, cart);
    }

    @RequestMapping(value = "/remove/{book_id}", method =
            RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable String book_id,
                           HttpServletRequest request) {
        String sessionId = request.getSession(true).getId();
        Order cart = cartService.read(sessionId); //320
        if(cart== null) {
            cart = cartService.create(new Order(sessionId));
        }
        Book product = bookService.getBookById(Integer.parseInt(book_id));
        if(product == null || product.isEmpty()) {
            throw new IllegalArgumentException(new
                    OwnException("no books: " + book_id));
        }
        cart.removeOrderItem(new OrderItem(product));
        cartService.update(sessionId, cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Illegal request, please try later")
            public void handleClientErrors(Exception ex) {
        ex.printStackTrace();

    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR,
            reason="Internal server error")
    public void handleServerErrors(Exception ex) {
        ex.printStackTrace();
    }

}

package springmvc.model.dao;

import springmvc.model.entities.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Component
public class ListDAO implements DAO {
    private Map<String, User> logined;
    private Map<Integer, Author> authors;
    private Map<Integer, List<Integer>> booksHasAuthors;
    private List<Book> books;

    public ListDAO() {
        logined = new HashMap<>();
        authors = new HashMap<>();
        booksHasAuthors = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        booksHasAuthors.put(1, list);
        books = new ArrayList<>();
        authors.put(1, new Author(1,"author", "surname", "about"));
        logined.put("log1", new User(2 ,"hello", "mysurname", "09590", "kk@gmail.com", true, "log1", "pass"));
    }


    public void connection() {

    }


    public void disconnection() {

    }

    @Override
    public Object getObjectById(int id) {
        return null;
    }

    public boolean login(User login) {
        if (logined.containsKey(login.getLogin())) {
            User buf = logined.get(login.getLogin());
            return buf.equals(login);
        }
        return false;
    }

    public boolean register(User user) {
        if (!logined.containsKey(user.getLogin())) {
            logined.put(user.getLogin(), user);
            return true;
        }
        return false;
    }

    public User getUser(String login) {
        return logined.get(login);
    }


    public Author getAuthor(int id) {
        return null;
    }


    public Item getGenre(int id) {
        return null;
    }


    public Book getBook(int id) {
        return null;
    }
}

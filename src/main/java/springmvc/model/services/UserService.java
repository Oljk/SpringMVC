package springmvc.model.services;

import org.springframework.stereotype.Service;
import springmvc.model.entities.User;

import java.util.List;


public interface UserService {
    User getUser(String login);
    User getUserById(int id);
    boolean addUser(User user);
    void delete(int id);
    List<User> getAll();
    User editUser(User user);
    User editUserWithSha(User user);
}

package springmvc.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.model.dao.UserDAO;
import springmvc.model.entities.User;
import springmvc.model.services.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User getUser(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.registr(user);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public User editUserWithSha(User user) {
        return null;
    }
}

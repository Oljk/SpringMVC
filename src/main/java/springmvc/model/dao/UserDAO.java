package springmvc.model.dao;

import springmvc.model.entities.*;

public interface UserDAO extends DAO {
    User getUserByLogin(String login);

    /**
     * for login user
     */
    boolean login(String login, String password);

    /**
     * for registration user
     * @param user
     * @return
     */
    boolean registr(User user);

    boolean login(User login);
}

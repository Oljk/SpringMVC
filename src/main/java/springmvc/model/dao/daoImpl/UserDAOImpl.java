package springmvc.model.dao.daoImpl;

import springmvc.model.dao.*;
import springmvc.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final DaoConnection Dao = DaoConnection.getInstance();

    private final String GET_LOGIN = "select login, password from users where login = ?";
    private final String CHECK_REGISTR = "SELECT  1 from USERS where login = ?";
    private final String INSERT_USER = "insert into users (user_id, name, surname, e_mail, password, login, phone_number) values (get_id.nextval, ?, ?, ?, ?, ?, ?)";
    private final String GET_OBJECT_BY_ID =
            "select user_id, name, surname, e_mail, password, login, phone_number from USERS where user_id = ?";
    private final String GET_OBJECT_BY_LOGIN =
            "select user_id, name, surname, e_mail, password, login, phone_number from USERS where login = ?";

    @Override
    public Object getObjectById(int id) {
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OBJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = parseUser(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return user;
    }


    @Override
    public User getUserByLogin(String login) {
        User user = null;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OBJECT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = parseUser(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return user;
    }


    @Override
    public boolean login(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())  {
                if (login.equals(resultSet.getString("LOGIN")) && password.equals(resultSet.getString("PASSWORD"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return true;
    }

    @Override
    public boolean login(User login) {
        return login.checkLogin() && login(login.getLogin(), login.getPassword());
    }

    @Override
    public boolean registr(User user) {
        if(!user.checkUser()) {
            return false;
        }
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_REGISTR)) {
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())  {
                return false;
            }

            try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getEmail());
                ps.setString(4,user.getPassword());
                ps.setString(5, user.getLogin());
                ps.setString(6, user.getPhone_number());
                ps.executeQuery();
            } catch (SQLException e) {
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return false;
    }
    private User parseUser(ResultSet resultSet) {
        User user = null;
        try {
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String e_mail = resultSet.getString("e_mail");
            String password = resultSet.getString("password");
            String login = resultSet.getString("login");
            String phone_number = resultSet.getString("phone_number");
            user = new User(user_id, name, surname,phone_number, e_mail, false, login,password);
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return user;
    }



}

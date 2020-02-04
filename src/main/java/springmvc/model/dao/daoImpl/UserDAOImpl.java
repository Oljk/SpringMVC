package springmvc.model.dao.daoImpl;

import springmvc.model.dao.*;
import springmvc.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void connection() {
        connection = DaoConnection.getConnection();
    }

    @Override
    public void disconnection() {
        try {
            DaoConnection.disconnection(preparedStatement, resultSet, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObjectById(int id) {
        return null;
    }

    @Override
    public Login getLoginByLogin(String login) {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }


    @Override
    public boolean login(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        connection();
        try {
            preparedStatement = connection.prepareStatement("SELECT  LOGIN, PASSWORD from USERS where login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())  {
                if (login.equals(resultSet.getString("LOGIN")) && password.equals(resultSet.getString("PASSWORD"))) {
                    return true;
                }
            }
        } catch (SQLException e) {}
        disconnection();
        return true;
    }

    @Override
    public boolean login(Login login) {
        return login.checkLogin() && login(login.getLogin(), login.getPassword());
    }

    @Override
    public boolean registr(User user) {
        if(!user.checkUser()) {
            return false;
        }
        connection();
        try {
            preparedStatement = connection.prepareStatement("SELECT  1 from USERS where login = ?");
            preparedStatement.setString(1, user.getLogin().getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())  {
                return false;
            }
        } catch (SQLException e) {}
        try {
            preparedStatement = connection.prepareStatement("insert into users (user_id, name, surname, e_mail, password, login, phone_number) \n" +
                    "values (get_id.nextval, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4,user.getLogin().getPassword());
            preparedStatement.setString(5, user.getLogin().getLogin());
            preparedStatement.setString(6, user.getPhone_number());
            preparedStatement.executeQuery();
        } catch (SQLException e) {

        }
        disconnection();
        return false;
    }


}

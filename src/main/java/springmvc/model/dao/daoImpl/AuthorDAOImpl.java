package springmvc.model.dao.daoImpl;


import org.springframework.beans.factory.annotation.Autowired;
import springmvc.model.dao.*;
import springmvc.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDAOImpl implements AuthorDAO {

    @Autowired
    CommonDAO commonDAO;

    private final DaoConnection Dao = DaoConnection.getInstance();


    private final String GET_AUTHORS_BY_BOOK = "select author_id from author_has_book where item_id = ?";
    private final String GET_OBJECT_BY_ID = "select author_id, name, surname, description from AUTHOR where author_id = ?";
    private final String GET_AUTHOR_ID = "select author_id from author where lower(name) = ? and lower(surname) = ?";
    private final String CREATE_AUTHOR = "insert into author (author_id, name, surname, description) values (?,?,?,?)";

    @Override
    public List<Author> getAuthorsByBook(int id) {
        ResultSet resultSet = null;
        List<Author> authorList = new ArrayList<>();
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AUTHORS_BY_BOOK)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int author_id = resultSet.getInt(1);
                Author author = (Author) getObjectById(author_id);
                authorList.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return  authorList;
    }

    @Override
    public List<Author> getAuthorsByBook(Book book) {
        int id = book.getItem().getItem_id();
        return getAuthorsByBook(id);
    }

    @Override
    public int addAuthor(Author author) {
        int id = commonDAO.getId();
        try (Connection connection = Dao.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUTHOR)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getSurname());
            preparedStatement.setString(4, author.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }


    @Override
    public Object getObjectById(int id) {
        Author author = null;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OBJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            author = parseAuthor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return author;
    }
    private Author parseAuthor(ResultSet resultSet) {
        Author author = null;
        try {
            int author_id = resultSet.getInt("AUTHOR_ID");
            String name = resultSet.getString("NAME");
            String surname = resultSet.getString("SURNAME");
            String description =  resultSet.getString("DESCRIPTION");
            author = new Author(author_id, name, surname, description);
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return author;
    }

    int getAutorByNameSurn(String name, String surname) {
       int id = -1;
       ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AUTHOR_ID)) {
            preparedStatement.setString(1, name.trim().toLowerCase());
            preparedStatement.setString(2, surname.trim().toLowerCase());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
           id = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return id;
    }

}

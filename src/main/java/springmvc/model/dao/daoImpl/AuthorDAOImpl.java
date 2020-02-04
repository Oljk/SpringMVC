package springmvc.model.dao.daoImpl;


import springmvc.model.dao.*;
import springmvc.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDAOImpl implements AuthorDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Author> getAuthorsByBook(int id) {
        List<Author> authorList = new ArrayList<>();
        connection();
        try {
            preparedStatement = connection.prepareStatement("select author_id\n" +
                    "from author_has_book\n" +
                    "where item_id = ?");
            resultSet = preparedStatement.executeQuery();
            preparedStatement.setInt(1, id);
            while (resultSet.next()) {
                int author_id = resultSet.getInt("AUTHOR_ID");
                Author author = (Author) getObjectById(author_id);
                authorList.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return  authorList;
    }

    @Override
    public List<Author> getAuthorsByBook(Book book) {
        int id = book.getItem().getItem_id();
        return getAuthorsByBook(id);
    }

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
        Author author = null;
        connection();
        try {
            preparedStatement = connection.prepareStatement("select \n" +
                    "author_id, name, surname, description" +
                    "from" +
                        "AUTHOR" +
                    "where" +
                        "author_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            author = parseAuthor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
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
}

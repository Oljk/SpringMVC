package springmvc.model.dao.daoImpl;



import org.springframework.stereotype.Repository;
import springmvc.model.dao.*;
import springmvc.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void connection() {
        connection = DaoConnection.getInstance().getConnection();
    }

    @Override
    public void disconnection() {
        try {
            DaoConnection.getInstance().disconnection(preparedStatement, resultSet, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getObjectById(int id) {
        Book book = null;
        connection();
        try {
            preparedStatement = connection.prepareStatement("select \n" +
                    "item_id, amount, year, price, publishing_house" +
                    " from" +
                    "  GOOD_ATTRS" +
                    " where" +
                    "  item_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            book = parseBook(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return book;
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> items = new ArrayList<>();
        connection();
        try {
            resultSet = connection.createStatement().executeQuery("select" +
                    " item_id, amount, year, price, publishing_house " +
                    "from " +
                    "GOOD_ATTRS");
            Book book;
            while (resultSet.next()) {
                System.out.println("hello");
                book = parseBook(resultSet);
                items.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return items;
    }

    @Override
    public List<Book> getBookByAuthor(Author author, String orderBy, boolean asc) {
        List<Book> listBook = new ArrayList<>();
        connection();
        try {
            preparedStatement = connection.prepareStatement("select item_id \n" +
                    "from good_attr s" +
                    "where " +
                    "AUTHOR_ID = ?");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int item_id = resultSet.getInt("ITEM_ID");
                BookDAO bookDAO = new BookDAOImpl();
                listBook.add((Book)bookDAO.getObjectById(item_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return listBook;
    }

    public List<Book> getBookByAuthor(Author author) {
        return getBookByAuthor(author, null, true);
    }

    @Override
    public boolean updateAmount(int id, int amount) {
        connection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update GOOD_ATTRS set AMOUNT = ? where ITEM_ID = ?");
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(1, amount);
            boolean ok = preparedStatement.executeUpdate() > 0;
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return false;
    }


    private Book parseBook(ResultSet resultSet) {
        Book book = null;
        try {
            int item_id = resultSet.getInt("ITEM_ID");
            String publishing_house = resultSet.getString("PUBLISHING_HOUSE");
            int year = resultSet.getInt("YEAR");
            int amount = resultSet.getInt("AMOUNT");
            double price = resultSet.getDouble("PRICE");
            DAO itemDao = new ItemDAOImpl();
            AuthorDAO authorDAO = new AuthorDAOImpl();
            Item bookItem = (Item)itemDao.getObjectById(item_id);
            List<Author> authorList =  authorDAO.getAuthorsByBook(item_id);
            book = new Book(bookItem, year, price, amount, publishing_house, authorList);
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return book;
    }

}

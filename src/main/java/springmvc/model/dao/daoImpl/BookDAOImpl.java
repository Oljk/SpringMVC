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

    private final DaoConnection Dao = DaoConnection.getInstance();

    private final String GET_OBJECT_BY_ID =
            "select item_id, amount, year, price, publishing_house from GOOD_ATTRS where item_id = ?";
    private final String GET_All_BOOKS = "select item_id, amount, year, price, publishing_house from GOOD_ATTRS";
    private final String GET_BOOK_BY_AUTHOR = "select item_id from good_attr s where AUTHOR_ID = ?";
    private final String UPDATE_BOOK_AMOUNT = "update GOOD_ATTRS set AMOUNT = ? where ITEM_ID = ?";

    @Override
    public Object getObjectById(int id) {
        Book book = null;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OBJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            book = parseBook(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> items = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_All_BOOKS)) {
            resultSet = preparedStatement.executeQuery();
            Book book;
            while (resultSet.next()) {
                book = parseBook(resultSet);
                items.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return items;
    }

    @Override
    public List<Book> getBookByAuthor(Author author, String orderBy, boolean asc) {
        List<Book> listBook = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_AUTHOR)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int item_id = resultSet.getInt("ITEM_ID");
                BookDAO bookDAO = new BookDAOImpl();
                listBook.add((Book) bookDAO.getObjectById(item_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           Dao.disconnection(resultSet);
        }
        return listBook;
    }

    public List<Book> getBookByAuthor(Author author) {
        return getBookByAuthor(author, null, true);
    }

    @Override
    public boolean updateAmount(int id, int amount) {
        boolean ok = false;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_AMOUNT)) {
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(1, amount);
            ok = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
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
            Item bookItem = (Item) itemDao.getObjectById(item_id);
            List<Author> authorList =  authorDAO.getAuthorsByBook(item_id);
            book = new Book(bookItem, year, price, amount, publishing_house, authorList);
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return book;
    }

}

package springmvc.model.dao.daoImpl;


import org.springframework.stereotype.Service;
import springmvc.model.entities.ItemType;
import springmvc.model.dao.*;
import springmvc.model.entities.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ItemDAOImpl implements ItemDAO {

    private final DaoConnection Dao = DaoConnection.getInstance();


    /**
     * пока неизвестно будем использовать или нет
     */
    private List<Item> Items;

    private final String GET_ALL_ITEMS = "select item_id, parent_id, name, type, description from items";
    private final String GET_THEMES = "select item_id, parent_id, name, type, description from  items where type = ?";
    private final String GET_BOOKS = "select item_id, parent_id, name, type, description from items where type = ?";
    private final String GET_OBJECT_BY_ID = "select item_id, parent_id, name, type, description from item where item_id = ?";

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ITEMS)) {
            resultSet = preparedStatement.executeQuery();
            Item item;
            while (resultSet.next()) {
                item = parseItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return items;
    }

    @Override
    public List<Item> getThemes() {
        List<Item> items = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_THEMES)) {
            preparedStatement.setInt(1, ItemType.THEME);
            resultSet = preparedStatement.executeQuery();
            Item item;
            while (resultSet.next()) {
                item = parseItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return items;
    }

    @Override
    public List<Item> getBooks() {
        List<Item> items = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKS)) {
            preparedStatement.setInt(1, ItemType.BOOK);
            resultSet = preparedStatement.executeQuery();
            Item item;
            while (resultSet.next()) {
                item = parseItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return items;
    }

    @Override
    public Object getObjectById(int id) {
        Item item = null;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_OBJECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item = parseItem(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.disconnection(resultSet);
        }
        return item;
    }

    private Item parseItem(ResultSet resultSet) {
        Item item = null;
       // Item parent = null; //если перент будет обьектом
        try {
            int item_id = resultSet.getInt("ITEM_ID");
            String name = resultSet.getString("NAME");
            int type = resultSet.getInt("TYPE");
            String description  = resultSet.getString("DESCRIPTION");
            Integer parent_id = resultSet.getInt("PARENT_ID");
           /* if (parent_id != null) {
                parent = ((Item)getObjectById(parent_id));
            }*/
            item = new Item(item_id,parent_id, name, type, description);
        } catch (SQLException e) {
            System.out.println("Get result error");
        }
        return item;
    }
}

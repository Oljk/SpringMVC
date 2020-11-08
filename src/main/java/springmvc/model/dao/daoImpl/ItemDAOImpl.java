package springmvc.model.dao.daoImpl;


import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CommonDAO commonDAO;


    /**
     * пока неизвестно будем использовать или нет
     */
    private List<Item> Items;

    private final String GET_ALL_ITEMS = "select item_id, parent_id, name, type, description from item";
    private final String GET_THEMES = "select item_id, parent_id, name, type, description from  item where type = ?";
    private final String GET_BOOKS = "select item_id, parent_id, name, type, description from item where type = ?";
    private final String GET_OBJECT_BY_ID = "select item_id, parent_id, name, type, description from item where item_id = ?";
    private final String CREATE_ITEM = "insert into item (item_id, parent_id, name, type, description ) values (?, ?, ?, ?, ?) ";

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
    public int addItem(Item item) {
        int id = commonDAO.getId();
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ITEM)) {
             preparedStatement.setInt(1, id);
             preparedStatement.setInt(2, item.getParent_id());
             preparedStatement.setString(3, item.getName());
             preparedStatement.setInt(4, item.getType().intValue());
             preparedStatement.setString(5, item.getDescription());
             int i = preparedStatement.executeUpdate();
             if (i < 0) id = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
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

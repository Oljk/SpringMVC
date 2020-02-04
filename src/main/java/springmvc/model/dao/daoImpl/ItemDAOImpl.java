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

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * пока неизвестно будем использовать или нет
     */
    private List<Item> Items;

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        connection();
        try {
            preparedStatement = connection.prepareStatement("select \n" +
                    "  item_id, parent_id, name, type, description \n" +
                    "from \n" +
                    "  items\n");
            resultSet = preparedStatement.executeQuery();
            Item item;
            while (resultSet.next()) {
                item = parseItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return items;
    }

    @Override
    public List<Item> getThemes() {
        List<Item> items = new ArrayList<>();
        connection();
        try {
            preparedStatement = connection.prepareStatement("select \n" +
                    "  item_id, parent_id, name, type, description \n" +
                    "from \n" +
                    "  items\n" +
                    "where type = ?;");
            preparedStatement.setInt(1, ItemType.THEME);
            resultSet = preparedStatement.executeQuery();
            Item item;
            while (resultSet.next()) {
                item = parseItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return items;
    }

    @Override
    public List<Item> getBooks() {
        List<Item> items = new ArrayList<>();
        connection();
        try {
            preparedStatement = connection.prepareStatement("select \n" +
                    "  item_id, parent_id, name, type, description \n" +
                    "from \n" +
                    "  items\n" +
                    "where type = ?;");
            preparedStatement.setInt(1, ItemType.BOOK);
            resultSet = preparedStatement.executeQuery();
            Item item;
            while (resultSet.next()) {
                item = parseItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
        return items;
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
        Item item = null;
        connection();
        try {
            preparedStatement = connection.prepareStatement("select \n" +
                    "  item_id, parent_id, name, type, description \n" +
                    "from \n" +
                    "  items\n" +
                    "where \n" +
                    "  item_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            item = parseItem(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnection();
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

package springmvc.model.dao.daoImpl;

import springmvc.model.dao.CommonDAO;
import springmvc.model.dao.DaoConnection;
import springmvc.model.entities.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonDAOImpl implements CommonDAO {

    private final DaoConnection Dao = DaoConnection.getInstance();
    private final static String GET_ID = "select get_id.nextval from dual ";

    @Override
    public int getId() {
        int id = -1;
        ResultSet resultSet = null;
        try (Connection connection = Dao.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ID)) {
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

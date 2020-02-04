package springmvc.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;



public class DaoConnection {
    private static String className;
    private static String url;
    private static String user;
    private static String password;
    private static DaoConnection instance = new DaoConnection();

    private DaoConnection () {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("resources/source.xml"));
            className = document.getElementsByTagName("driver-class").item(0).getTextContent();
            url = document.getElementsByTagName("connection-url").item(0).getTextContent();
            user = document.getElementsByTagName("user-name").item(0).getTextContent();
            password = document.getElementsByTagName("password").item(0).getTextContent();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(className);
            Locale.setDefault(Locale.ENGLISH);
            connection = DriverManager.getConnection(url, user,password);
            if (connection.isClosed()) {
                System.out.println("Connection error!");
                //logs
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No driver");
            //logs
        } catch (SQLException e) {
            System.out.println("Connection to database is failed");
            //logs
        }
        return connection;
    }

    public static void disconnection(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection )
            throws SQLException {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
    }
}

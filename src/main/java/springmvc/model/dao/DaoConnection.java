package springmvc.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import springmvc.config.ApplicationContextConfig;
import springmvc.listeners.ContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.validation.ReportAsSingleViolation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;


@Service
public class DaoConnection {

    private static String className;
    private static String url;

    private static String user;

    private static String password;
    private static DaoConnection instance = new DaoConnection();
    private boolean init = false;



    private DaoConnection () {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("db/source.xml"));
            className = document.getElementsByTagName("driver-class").item(0).getTextContent();
            url = document.getElementsByTagName("connection-url").item(0).getTextContent();
            user = document.getElementsByTagName("user-name").item(0).getTextContent();
            password = document.getElementsByTagName("password").item(0).getTextContent();
            if (!init) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("db/model.sql")));
                StringBuffer sb = new StringBuffer();
                String str;
                while((str = bufferedReader.readLine())!= null) {
                    sb.append(str + "\n");
                }
                String sql = sb.toString();
                try {
                    getConnection().createStatement().execute(sql);
                    init = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                    //log
                }
            }
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

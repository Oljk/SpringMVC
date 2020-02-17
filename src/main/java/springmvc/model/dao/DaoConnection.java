package springmvc.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
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

    private static Logger logger = LoggerFactory.getLogger(DaoConnection.class);

    private static String className;
    private static String url;

    private static String user;

    private static String password;
    private static DaoConnection instance = new DaoConnection();
    private boolean init = false;

    public static DaoConnection getInstance() {
        return instance;
    }

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
                    logger.trace("db init");
                } catch (SQLException e) {
                    logger.warn("init db: " + e.getSQLState() + e.getStackTrace().toString());
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            logger.error("create connection: " + e.getMessage() +  " - "  + e.getStackTrace().toString());
            System.out.println("create conn" + e.getStackTrace().toString() + " - " + e.getMessage());
        }
        logger.trace("ceated connection");
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(className);
            Locale.setDefault(Locale.ENGLISH);
            connection = DriverManager.getConnection(url, user,password);
            if (connection.isClosed()) {
                logger.warn("Connection error");
                System.out.println("connectio error");
            }
        } catch (ClassNotFoundException e) {
            logger.error("No driver\n" + e.getMessage());
            System.out.println("no driver");
        } catch (SQLException e) {
            logger.error("Connection to database is failed\n" + e.getStackTrace().toString());
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnection(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection )
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

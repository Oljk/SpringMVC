package springmvc.model.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springmvc.model.OwnExeption;
import springmvc.model.dao.daoImpl.*;

@Configuration
public class ConfigurationDAO {

    @Bean
    public DAO dao() {
        return new ListDAO();
    }

    @Bean
    public AuthorDAO getAuthorDao() {
        return new AuthorDAOImpl();
    }
    @Bean
    public ItemDAO getItemDao() {
        return new ItemDAOImpl();
    }
    @Bean
    public OrderDAO getOrderDao() {
        return new OrderDAOImpl();
    }
    @Bean
    public UserDAO getUserDao() {
        return new UserDAOImpl();
    }

    @Bean
    public BookDAO getBookDao(){
        return new BookDAOImpl();
    }

    @Bean
    public CommonDAO getCommonDAO() {
        return new CommonDAOImpl();
    }

}

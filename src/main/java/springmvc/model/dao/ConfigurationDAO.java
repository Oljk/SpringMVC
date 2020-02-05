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
    public AuthorDAO getAuthorDao() throws OwnExeption {
        return new AuthorDAOImpl();
    }
    @Bean
    public ItemDAO getItemDao() throws OwnExeption {
        return new ItemDAOImpl();
    }
    @Bean
    public OrderDAO getOrderDao() throws OwnExeption {
        return new OrderDAOImpl();
    }
    @Bean
    public UserDAO getUserDao() throws OwnExeption {
        return new UserDAOImpl();
    }

}

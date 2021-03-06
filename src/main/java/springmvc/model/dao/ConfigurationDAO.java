package springmvc.model.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springmvc.model.dao.daoImpl.*;

@Configuration
public class ConfigurationDAO {

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
    public CommonDAO getCommonDAO() {
        return new CommonDAOImpl();
    }

}

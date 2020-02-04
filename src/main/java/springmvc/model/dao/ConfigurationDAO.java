package springmvc.model.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springmvc.model.OwnExeption;
import springmvc.model.dao.daoImpl.AuthorDAOImpl;
import springmvc.model.entities.*;

@Configuration
public class ConfigurationDAO {

    @Bean
    public DAO dao() {
        return new ListDAO();
    }

  /*  @Bean
    public DAO getDao(Author author) throws OwnExeption {
        return new AuthorDAOImpl();
    }*/
}

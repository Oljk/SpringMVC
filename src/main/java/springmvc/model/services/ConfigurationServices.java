package springmvc.model.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springmvc.model.services.impl.CurrentOrderServiceImpl;
import springmvc.model.services.impl.OrderServiceImpl;
import springmvc.model.services.impl.UserServiceImpl;

@Configuration
public class ConfigurationServices {

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public BookService getBookService() {
        return new BookService();
    }

    @Bean
    public CurrentOrderService getCurrentOrderRepository() {
        return new CurrentOrderServiceImpl();
    }
}

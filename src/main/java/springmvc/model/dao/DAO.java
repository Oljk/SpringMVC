package springmvc.model.dao;

import org.springframework.stereotype.Component;

@Component
public interface DAO {
    void connection();
    void disconnection();
    Object getObjectById(int id);
}

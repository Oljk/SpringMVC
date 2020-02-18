package springmvc.model.dao;

import org.springframework.stereotype.Component;

@Component
public interface DAO {
    Object getObjectById(int id);
}

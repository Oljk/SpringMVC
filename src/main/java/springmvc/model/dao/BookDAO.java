package springmvc.model.dao;


import org.springframework.stereotype.Repository;
import springmvc.model.entities.*;

import java.util.List;

@Repository
public interface BookDAO extends DAO {
    List<Book> getAllBooks();

    /**
     *
     * @param author обьект автора
     * @param orderBy - столбец по которому выполнить сортировку (дефолтно = id автора, если будет что-то ложное)
     * @param asc 1 если asc, 2 если desc
     * @return
     */
    List<Book> getBookByAuthor(Author author, String orderBy, boolean asc);

}

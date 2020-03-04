package springmvc.model.dao;

import springmvc.model.entities.*;

import java.util.List;

public interface AuthorDAO extends DAO {
    List<Author> getAuthorsByBook(int id);
    List<Author> getAuthorsByBook(Book book);
    int addAuthor(Author author);

    /**
     * возвращает айди автора по имени/фамиилии не кейс чувствительный
     * @param name
     * @param surname
     * @return
     */
    int getAutorByNameSurn(String name, String surname);
}

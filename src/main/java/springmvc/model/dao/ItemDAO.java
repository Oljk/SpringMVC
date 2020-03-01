package springmvc.model.dao;


import springmvc.model.entities.*;

import java.util.List;


public interface ItemDAO extends DAO {
   List<Item> getAllItems();
   List<Item> getThemes();
   List<Item> getBooks();
   int addItem(Item item);
}

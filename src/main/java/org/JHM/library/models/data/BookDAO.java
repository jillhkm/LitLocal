package org.JHM.library.models.data;

import org.JHM.library.models.objects.Book;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface BookDAO extends CrudRepository<Book, Integer> {

    ArrayList<Book> getBooksByBookshelfAndUserid(String Bookshelf, int UserID);

    Book getFirstByTitleAndUserid(String title, int UserID);

    void deleteBookByTitleAndUserid(String title, int UserId);
}

package org.JHM.library.models.data;


import org.JHM.library.models.objects.Bookshelves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.ArrayList;


@Transactional
@Repository
public interface BookshelvesDAO extends CrudRepository<Bookshelves, Integer> {

    ArrayList<Bookshelves> getBookshelvesByUserID(int UserID);

   // void deleteBookshelvesByBookshelfAndUserID (String bookshelf, int UserID);
}

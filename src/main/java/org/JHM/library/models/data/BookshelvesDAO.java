package org.JHM.library.models.data;


import org.JHM.library.models.Bookshelves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface BookshelvesDAO extends CrudRepository<Bookshelves, Integer> {

    ArrayList<Bookshelves> getBookshelvesByUserID(int UserID);
}

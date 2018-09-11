package org.JHM.library.models.data;

import org.JHM.library.models.objects.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Integer> {
}

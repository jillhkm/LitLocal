package org.JHM.library.models.data;

import org.JHM.library.Controllers.HomeController;
import org.JHM.library.models.Bookshelves;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookshelvesData {

    @Autowired
    BookshelvesDAO bookshelvesdao;

    public List<String> getBookshelf() {
        ArrayList<Bookshelves> bookshelfobj = bookshelvesdao.getBookshelvesByUserID(HomeController.thisuser);
        ArrayList<String> bookshelves= new ArrayList<>();
        for (Bookshelves item : bookshelfobj) {
            bookshelves.add(item.getBookshelf());

        }
    }
}

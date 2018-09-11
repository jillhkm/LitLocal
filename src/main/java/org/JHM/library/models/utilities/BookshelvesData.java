package org.JHM.library.models.utilities;

import org.JHM.library.Controllers.HomeController;
import org.JHM.library.models.data.BookshelvesDAO;
import org.JHM.library.models.objects.Bookshelves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookshelvesData {

    @Autowired
    BookshelvesDAO bookshelvesdao;


    public ArrayList<String> getBookshelf() {
        ArrayList<Bookshelves> bookshelfobj = bookshelvesdao.getBookshelvesByUserID(HomeController.thisuser);
        ArrayList<String> bookshelves= new ArrayList<>();
        for (Bookshelves item : bookshelfobj) {
            bookshelves.add(item.getBookshelf());
        }
        return bookshelves;
    }


}

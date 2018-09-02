package org.JHM.library.Controllers;

import org.JHM.library.models.Bookshelves;
import org.JHM.library.models.data.BookshelvesDAO;
import org.JHM.library.models.data.BookshelvesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BookController {

    @Autowired
    BookshelvesDAO bookshelvesdao;

    @GetMapping(value="/addbook")
    public String addbook(Model model) {
        if (!HomeController.loggedin) {
            return "redirect: ";
        } else {
            model.addAttribute("bookshelves", BookshelvesData.getBookshelf());
            return "addbook.html";
        }
    }
}

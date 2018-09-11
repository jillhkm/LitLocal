package org.JHM.library.Controllers;

import org.JHM.library.models.data.BookDAO;
import org.JHM.library.models.data.BookshelvesDAO;
import org.JHM.library.models.objects.Book;
import org.JHM.library.models.objects.Bookshelves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;

@Controller
public class BookController {

    @Autowired
    BookDAO bookdao;

    @Autowired
    BookshelvesDAO bookshelvesdao;

    //globals
    boolean showerror = false;
    String errmsg = "";
    String savedauthor = "";
    String savedesc = "";



    @GetMapping(value = "/addbook")
    public String addbook(Model model) {
        if (!HomeController.loggedin) {
            return "redirect: ";
        } else {
            ArrayList<Bookshelves> books = new ArrayList<>();
            books = bookshelvesdao.getBookshelvesByUserID(HomeController.thisuser);
            ArrayList<String> bookshelves = new ArrayList<>();
            for (Bookshelves item : books) {
                bookshelves.add(item.getBookshelf());
            }
            model.addAttribute("showerror", showerror);
            model.addAttribute("errormsg", errmsg);
            model.addAttribute("author", savedauthor);
            model.addAttribute("desc", savedesc);
            model.addAttribute("bookshelveslist", bookshelves);
            return "addbook.html";
        }
    }


    @PostMapping (value="/attemptadd")
    public String attemptadd(@RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String description,
                             @RequestParam String bookshelf,
                             @RequestParam String ownstat,
                             @RequestParam String view,
                             @RequestParam String rating,
                             @RequestParam String haveread,
                             @RequestParam String rdate) {

        //checking if title is valid
        if (title.isEmpty()) {
            errmsg = "Book must have a title";
            showerror = true;
            savedauthor = author;
            savedesc = description;
            return "redirect:/addbook";
        }

        //getting int of rating
        if (rating.isEmpty()) {
            rating = "0";
        }
        int rate = Integer.valueOf(rating);

        //getting date
        java.sql.Date date;
        if (rdate.isEmpty()) {
             date = null;
        } else {
             date = Date.valueOf(rdate);
        }

        //adding new book
        Book book = new Book(title, HomeController.thisuser, author, description, bookshelf, ownstat, view, rate, haveread, date);
        bookdao.save(book);
        errmsg="";
        showerror = false;
        savedesc = "";
        savedauthor = "";
        return "redirect:/addbook";
    }

}

package org.JHM.library.Controllers;

import org.JHM.library.models.data.BookDAO;
import org.JHM.library.models.data.BookshelvesDAO;
import org.JHM.library.models.objects.Book;
import org.JHM.library.models.objects.Bookshelves;
import org.JHM.library.models.objects.NYTbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class BookController {

    @Autowired
    BookDAO bookdao;

    @Autowired
    BookshelvesDAO bookshelvesdao;


    //globals
    boolean showerror = true;
    String errmsg = "";
    String savedauthor = "";
    String savedesc = "";
    String savetitle = "";



    @GetMapping(value="/addbook")
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
            model.addAttribute("title", savetitle);
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

        //check if title already exists
        Book titlecheck = bookdao.getFirstByTitleAndUserid(title, HomeController.thisuser);
        if (titlecheck != null) {
            errmsg = "Book with that title already exists on your bookshelves.";
            showerror = true;
            savedauthor = "";
            savedesc = description;
            return "redirect:/addbook";
        }

        //getting int of rating
        if (rating.isEmpty()) {
            rating = "0";
        }
        int rate = Integer.valueOf(rating);

        if (description.isEmpty()) {
            description = "";
        }

        if (author.isEmpty()) {
            author = "";
        }

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
        savetitle = "";
        return "redirect:/addbook";
    }

    @PostMapping(value="/addrecbook")
    public String addrecbook(@RequestParam String[] nytbook)
    {
        savedauthor = nytbook[1];
        savedesc = nytbook[2];
        savetitle = nytbook[0];
        return "redirect:/addbook";
    }

    @GetMapping(value="/index")
    public String index(Model model)
    {
        //check login
        if (!HomeController.loggedin) {
            return "redirect: ";
        }


        //get bookshelves
        ArrayList<Bookshelves> books = new ArrayList<>();
        books = bookshelvesdao.getBookshelvesByUserID(HomeController.thisuser);

        int UserID = HomeController.thisuser;
        //get books
        HashMap<String, ArrayList<Book>> booksonshelves = new HashMap<>();
        for (Bookshelves item : books) {
            String bookshelf = item.getBookshelf();
            booksonshelves.put(bookshelf, bookdao.getBooksByBookshelfAndUserid(bookshelf, UserID));
        }

        model.addAttribute("bookshelflist", booksonshelves);

        return "index.html";
    }

    @GetMapping(value="/remove")
    public String remove(Model model)
    {
        //check login
        if (!HomeController.loggedin) {
            return "redirect: ";
        }


        //get bookshelves
        ArrayList<Bookshelves> books = new ArrayList<>();
        books = bookshelvesdao.getBookshelvesByUserID(HomeController.thisuser);

        int UserID = HomeController.thisuser;
        //get books
        HashMap<String, ArrayList<Book>> booksonshelves = new HashMap<>();
        for (Bookshelves item : books) {
            String bookshelf = item.getBookshelf();
            booksonshelves.put(bookshelf, bookdao.getBooksByBookshelfAndUserid(bookshelf, UserID));
        }

        model.addAttribute("bookshelflist", booksonshelves);

        return "remove.html";
    }

    @PostMapping(value="/removebooks")
    public String RemoveForm(@RequestParam String[] bookids) {
        int userId = HomeController.thisuser;
        for (String bookid : bookids) {
            bookdao.deleteBookByTitleAndUserid(bookid, userId);
        }
        return "redirect:/index";
    }

    @GetMapping(value="/book/{title}")
    public String viewBook(Model model, @PathVariable("title") String title) {
        Book thisbook = bookdao.getFirstByTitleAndUserid(title, HomeController.thisuser);
        model.addAttribute(thisbook);
        return "bookview.html";
    }

}

package org.JHM.library.Controllers;

import org.JHM.library.models.data.BookshelvesDAO;
import org.JHM.library.models.objects.Bookshelves;
import org.JHM.library.models.objects.NYTbook;
import org.JHM.library.models.objects.User;
import org.JHM.library.models.data.UserDAO;
import org.JHM.library.models.utilities.UserData;
import org.JHM.library.util.BCrypt;
import org.JHM.library.util.NYTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;


@Controller
public class HomeController {

    @Autowired
    UserDAO userdao;

    @Autowired
    BookshelvesDAO bookshelvedao;


    static public Boolean loggedin = true;
    static public int thisuser = 1;

   ArrayList<NYTbook> fiction = new ArrayList<>();
   ArrayList<NYTbook> nonfiction = new ArrayList<>();

   String nameLabel = "Name";
   String emailLabel = "E-Mail";
   String passwordLabel = "Password";
   String confirmLabel = "Confirm Password";
   Boolean rcover = true;
   Boolean goodlogin = true;
   Boolean badlogin = false;


    @GetMapping(value ="")
    public String login(Model model) {
        if (loggedin) {
            return "redirect:/index";
        } else {
            model.addAttribute("rcover", rcover);
            model.addAttribute("rname", nameLabel);
            model.addAttribute("remail", emailLabel);
            model.addAttribute("rpassword", passwordLabel);
            model.addAttribute("rconfirm", confirmLabel);
            model.addAttribute("goodlogin", goodlogin);
            model.addAttribute("badlogin", badlogin);
            return "loginregister.html";
        }
    }


    @PostMapping(value="/attemptlogin")
    public String login(@RequestParam String email, @RequestParam String password) {
        ArrayList<User> users = new ArrayList<>();
        users = userdao.getAllByEmail(email);
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                //check if password is same
                boolean pass = BCrypt.checkpw(password, user.getPassword());
                if (pass) {
                    loggedin = true;
                    thisuser = user.getID();
                    return "redirect: /addbook";
                } else {
                    loggedin = false;
                    goodlogin = false;
                    badlogin = true;
                    return "redirect: ";
                }
            } else {
                loggedin = false;
                goodlogin = false;
                badlogin = true;
                return "redirect ";
            }
        }
        //if error return incorrect login (Should not reach this point)
        loggedin = false;
        goodlogin = false;
        badlogin = true;
        return "redirect: ";
    }


    @PostMapping(value="/attemptregister")
    public String processNewUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirm
    ) {
        int results;
        UserData userData = new UserData();
        ArrayList<User> emails = new ArrayList<User>();
        //check if user exists
        emails = userdao.getAllByEmail(email);
        if (!(emails.isEmpty())) {
            return "redirect:/existinguser";
        } else {
            results = userData.add(name, email, password, confirm);
            switch (results) {
                case 0:
                    password = BCrypt.hashpw(password, BCrypt.gensalt());
                    User newUser = new User(name, email, password);
                    userdao.save(newUser);
                    User userID = userdao.getFirstByEmail(email);
                    thisuser = userID.getID();

                    //how can I move this to its own class?
                    //autowire won't work with static, how to make nonstatic class to call here?
                    ArrayList<Bookshelves> main = new ArrayList<>();
                    Bookshelves one = new Bookshelves(thisuser, "Read");
                    Bookshelves two = new Bookshelves (thisuser, "To Read");
                    Bookshelves three = new Bookshelves(thisuser, "Currently Reading");
                    main.add(one);
                    main.add(two);
                    main.add(three);
                    bookshelvedao.saveAll(main);


                    loggedin = true;
                    return "redirect:/addbook";
                case 1:
                    emailLabel = UserData.geterr();
                    passwordLabel = "Password";
                    confirmLabel = "Confirm Password";
                    rcover = false;
                    return "redirect: ";
                case 2:
                    passwordLabel = UserData.geterr();
                    emailLabel = "E-Mail";
                    confirmLabel = "Confirm Password";
                    rcover = false;
                    return "redirect: ";
                case 3:
                    passwordLabel = UserData.geterr();
                    confirmLabel = UserData.geterr();
                    emailLabel = "E-Mail";
                    rcover = false;
                    return "redirect: ";
                default:
                    return "redirect: ";
            }
        }
    }


    @GetMapping(value="/existinguser")
    public String existing(Model model) {
        model.addAttribute("goodlogin", goodlogin);
        model.addAttribute("badlogin", badlogin);
        return "existing.html";
    }


    @GetMapping(value="/newreads")
    public String newreads(Model model) throws IOException {
        if (loggedin) {
            fiction.clear();
            fiction = NYTHandler.createArray(true);
            nonfiction.clear();
            nonfiction = NYTHandler.createArray(false);
            model.addAttribute("NYTbooks", fiction);
            model.addAttribute("NYTbooknons", nonfiction);
            return "newreads.html";
        } else {
            return "redirect: ";
        }
    }

}


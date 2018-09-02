package org.JHM.library.Controllers;

import org.JHM.library.models.NYTbook;
import org.JHM.library.models.User;
import org.JHM.library.models.data.UserDAO;
import org.JHM.library.models.data.UserData;
import org.JHM.library.util.BCrypt;
import org.JHM.library.util.NYTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


@Controller
public class HomeController {

    @Autowired
    UserDAO userdao;

    public Boolean loggedin = true;
    public int thisuser;

    public boolean getlog() {
        return this.loggedin;
    }

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
            return "redirect: ";
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
        for (User user : users) {
            if (user.getEmail() == email ) {
                //check if password is same
                boolean pass = BCrypt.checkpw(password, user.getPassword());
                if (pass) {
                    loggedin = true;
                    thisuser = userdao.getFirstByEmail(email).getID();
                    return "landing.html";
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
        loggedin = true;
        goodlogin = false;
        badlogin = true;
        return "redirect";
    }


    @RequestMapping(value="attemptregister", method = RequestMethod.POST)
    public String processNewUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirm
    )
    {
        int results;
        results = UserData.add(name, email, password, confirm);
        switch (results) {
            case 0: loggedin = true;
                    return "redirect: ";
            case 1: emailLabel = UserData.geterr();
                    passwordLabel = "Password";
                    confirmLabel = "Confirm Password";
                    rcover = false;
                    return "redirect: ";
            case 2: passwordLabel = UserData.geterr();
                    emailLabel="E-Mail";
                    confirmLabel = "Confirm Password";
                    rcover = false;
                    return "redirect: ";
            case 3: passwordLabel = UserData.geterr();
                    confirmLabel = UserData.geterr();
                    emailLabel = "E-Mail";
                    rcover = false;
                    return "redirect: ";
            case 4: return "redirect: /existinguser";
            default: return "redirect: ";
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


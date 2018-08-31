package org.JHM.library.models.data;

import org.JHM.library.models.User;
import org.JHM.library.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class UserData {

    @Autowired
    private static UserDAO userDAO;

    static String errmsg = "";

    public static int add(String name, String email, String password, String confirm) {
        ArrayList<User> emails = new ArrayList<User>();
        //check if user exists
        emails = userDAO.getAllByEmail(email);
        for (User users : emails) {
            if (users.getEmail() == email) {
                return 4;
            }
        } if (email.isEmpty() || !((email.contains("@")) && email.contains("."))) {
            errmsg = "Enter a valid email";
            return 1;
        //check password length
        } else if (password.length() < 6 ) {
            errmsg = "PWs must be 6+ characters";
            return 2;
        //check confirm matches
        } else if (!password.equals(confirm)) {
            errmsg = "PWs do not match";
            return 3;
        //adds user w/ encrypted password
        } else {
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            User newUser = new User(name, email, password);
            userDAO.save(newUser);
            return 0;
        }
    }

    public static String geterr() {
        return errmsg;
    }

}


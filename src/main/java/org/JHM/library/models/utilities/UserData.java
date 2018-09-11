package org.JHM.library.models.utilities;

import org.JHM.library.models.data.UserDAO;
import org.JHM.library.models.objects.Bookshelves;
import org.JHM.library.models.objects.User;
import org.JHM.library.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.ArrayList;

public class UserData {


    static String errmsg = "";

    public int add(String name, String email, String password, String confirm) {
         if (email.isEmpty() || !((email.contains("@")) && email.contains("."))) {
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
            return 0;
        }
    }

    public static String geterr() {
        return errmsg;
    }

}


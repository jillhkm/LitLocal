package org.JHM.library.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bookshelves {

    private int UserID;

    private String Bookshelf;

    @Id
    @GeneratedValue
    private int ID;

    public Bookshelves(int UserID, String bookshelf) {
        this.UserID = UserID;
        this.Bookshelf = bookshelf;
    }

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return UserID;
    }

    public String getBookshelf() {
        return Bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        Bookshelf = bookshelf;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}

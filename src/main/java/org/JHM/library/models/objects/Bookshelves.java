package org.JHM.library.models.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bookshelves {

    private int userID;

    private String Bookshelf;

    @Id
    @GeneratedValue
    private int ID;

    public Bookshelves(int UserID, String Bookshelf) {
        this.userID = UserID;
        this.Bookshelf = Bookshelf;
    }

    public Bookshelves() {}

    public int getID() {
        return ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int UserID) {
        this.userID = UserID;
    }

    public String getBookshelf() {
        return Bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        Bookshelf = bookshelf;
    }
}

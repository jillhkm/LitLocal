package org.JHM.library.models.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int ID;

    private String title;

    private int UserID;

    private String author;

    private String description;

    private String bookshelf;

    private String ownstat;

    private String view;

    private int rating;

    private String haveread;

    private java.sql.Date rdate;

    public Book(String title, int UserID, String author, String description, String bookshelf, String ownstat, String view, int rating, String haveread, java.sql.Date rdate) {
        this.title = title;
        this.UserID = UserID;
        this.author = author;
        this.description = description;
        this.bookshelf = bookshelf;
        this.ownstat = ownstat;
        this.view = view;
        this.rating = rating;
        this.haveread = haveread;
        this.rdate = rdate;
    }

    public Book() { }

    public int getID() {
        return this.ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getdescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getOwnstat() {
        return ownstat;
    }

    public void setOwnstat(String ownstat) {
        this.ownstat = ownstat;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getrdate() {
        return rdate;
    }

    public void setrdate(Date rdate) {
        this.rdate = rdate;
    }

    public void setHaveread(String haveread) {this.haveread = haveread;}

    public String getHaveread() {return this.haveread;}
}

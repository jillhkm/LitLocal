package org.JHM.library.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int ID;

    @NotNull
    private String title;

    private String author;

    private String desc;

    private String bookshelf;

    private String ownstat;

    private String view;

    private int rating;

    public Book(String title, String author, String desc, String bookshelf, String ownstat, String view, int rating) {
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.bookshelf = bookshelf;
        this.ownstat = ownstat;
        this.view = view;
        this.rating = rating;
    }

    public Book(){}

    public int getID() {
        return this.ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}

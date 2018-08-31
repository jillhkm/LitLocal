package org.JHM.library.models;

public class NYTbook {

    private String title;

    private String author;

    private String desc;

    public NYTbook(String title, String author, String desc) {
        this.title = title;
        this.author = author;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public NYTbook(){}
}

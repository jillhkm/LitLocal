package org.JHM.library.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {


    @Id
    @GeneratedValue
    private int ID;

    private String password;

    private String name;

    private String email;

    public User(String name, String email, String password) {
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User() { }

    public int getID() {    return ID;  }

    public String getPassword() {   return password;    }

    public void setPassword(String Password) {
        this.password = (String) password;
    }

    public String getName() {   return name;    }

    public void setName(String Name) {  this.name = Name;   }

    public String getEmail() {  return email;   }

    public void setEmail(String email) {    this.email = email;   }

}

package com.example.jvw91.udemyidinformation.database;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Object for holding data when writing or reading data from the database.
 */
public class Contact implements Serializable {
    private String cName, fName, lName, phoneNum, mail, id;

    public Contact() {
    }

    public Contact(String id, String cName, String fName, String lName, String phoneNum, String mail) {
        this.id = id;
        this.cName = cName;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.mail = mail;
    }

    /**
     * Uses reflection to check if this object is empty.
     * Checks all string fields of contact.
     *
     * @return
     */
    public boolean isEmpty() {
        ArrayList<Boolean> bList = new ArrayList<>();

        //Reflects this object
        try {
            for (Field field : this.getClass().getDeclaredFields()) {
                Object value = field.get(this);
                if (field.getType().isAssignableFrom(String.class)) {
                    if (value == null || value.equals("")) {
                        bList.add(true);
                    } else {
                        bList.add(false);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //If List contains false, contact is not empty.
        if (bList.contains(false)) {
            return false;
        } else {
            return true;
        }
    }

    //Getters & Setters
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}
}

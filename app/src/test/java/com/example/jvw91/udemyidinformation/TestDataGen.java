package com.example.jvw91.udemyidinformation;

import com.example.jvw91.udemyidinformation.database.Contact;

public class TestDataGen {

    public Contact testContact() {
        Contact contact = new Contact();
        contact.setMail("test@test.nl");
        contact.setcName("Company");
        contact.setfName("Jaap");
        contact.setlName("Hakker");
        return contact;
    }

}

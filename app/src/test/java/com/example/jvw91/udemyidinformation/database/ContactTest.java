package com.example.jvw91.udemyidinformation.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void testIsEmpty() {
        Contact contact = new Contact();
        assertEquals(true, contact.isEmpty());

        contact.setfName("Jaap");
        assertEquals(false, contact.isEmpty());
    }

}
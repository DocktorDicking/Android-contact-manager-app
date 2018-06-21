package com.example.jvw91.udemyidinformation.database;

import android.content.Context;

import com.example.jvw91.udemyidinformation.BuildConfig;
import com.example.jvw91.udemyidinformation.TestDataGen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DbHelperTest {
    Context context;
    DbHelper db;
    final String TAG = "Unit:DbHelperTest: ";

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        db = new DbHelper(context);
    }

    @After
    public void tearDown() {
        db.clearDbAndRecreate(); //Clears database and makes a new one.
    }

    /**
     * Tests if a contact can be saved.
     */
    @Test
    public void addContactToDB() {
        Contact contact = new TestDataGen().testContact();
        boolean sql = db.storeContact(contact);
        assertEquals(true, sql); //StoreContact returns success
    }

    /**
     * Tests if a contact can be retrieved.
     */
    @Test
    public void retrieveContact(){
        final String METHODTAG = TAG + "test: retrieveContact: ";
        Contact contact = new TestDataGen().testContact();
        boolean sql = db.storeContact(contact);
        if (sql) {
            ArrayList<Contact> contacts = db.getContacts();
            assertEquals(1, contacts.size());
            //Asserts returned contact values.
            assertEquals(contact.getcName(), contacts.get(0).getcName());
            assertEquals(contact.getfName(), contacts.get(0).getfName());
            assertEquals(contact.getlName(), contacts.get(0).getlName());
            assertEquals(contact.getMail(), contacts.get(0).getMail());
        } else {
            //If sql fails, the whole test fails.
            fail(METHODTAG + "Could not retrieve contacts");
        }
    }

    /**
     * Tests if a contact can be updated.
     */
    @Test
    public void updateContact() {
        final String METHODTAG = TAG + "test: updateContact: ";
        Contact contact = new TestDataGen().testContact();

        if (db.storeContact(contact)) {
            ArrayList<Contact> contacts = db.getContacts();
            if (contacts.size() > 0) {
                contact = contacts.get(0);
                contact.setcName("Hema");
                contact.setPhoneNum("0625887954");
                if (db.updateContact(contact)) {
                    contacts = db.getContacts();
                    assertEquals("Hema", contacts.get(0).getcName());
                    assertEquals("0625887954", contacts.get(0).getPhoneNum());
                } else {
                    fail(METHODTAG + "Could not update contact");
                }
            } else {
                //If no contacts are returned
                fail(METHODTAG + "Could not retrieve contact");
            }
        } else {
            //If sql fails
            fail(METHODTAG + "Could not store contact.");
        }

    }

    /**
     * Tests if a contact can be deleted
     */
    @Test
    public void deleteContact() {
        final String METHODTAG = TAG + "test: deleteContact: ";
        Contact contact = new TestDataGen().testContact();

        if (db.storeContact(contact)) {
            ArrayList<Contact> contacts = db.getContacts();
            if (contacts.size() > 0){
                if (db.deleteContact(contacts.get(0))) {
                    contacts = db.getContacts();
                    assertEquals(0, contacts.size());
                }
            } else {
                fail(METHODTAG + "Could not retrieve contact");
            }
        } else {
            fail(METHODTAG + "Could not save contact");
        }
    }

}
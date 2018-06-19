package com.example.jvw91.udemyidinformation.database;

import android.content.Context;

import com.example.jvw91.udemyidinformation.BuildConfig;
import com.example.jvw91.udemyidinformation.TestDataGen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DbHelperTest {
    Context context;
    DbHelper db;

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
            fail();
        }
    }

    @Test
    public void updateContact() {
        Contact contact = new TestDataGen().testContact();
        boolean sql = db.storeContact(contact);
    }



}
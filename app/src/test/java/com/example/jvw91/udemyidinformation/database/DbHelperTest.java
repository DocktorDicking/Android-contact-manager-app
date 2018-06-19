package com.example.jvw91.udemyidinformation.database;

import android.content.Context;

import com.example.jvw91.udemyidinformation.BuildConfig;
import com.example.jvw91.udemyidinformation.TestDataGen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application;
        db = new DbHelper(context);
    }

    @After
    public void tearDown() {
        db.clearDbAndRecreate(); //Clears database and makes a new one.
    }

    @Test
    public void addContactToDB() {
        Contact contact = new TestDataGen().testContact();
        boolean sql = db.storeContact(contact);
        ArrayList<Contact> contacts = db.getContacts();
    }

}
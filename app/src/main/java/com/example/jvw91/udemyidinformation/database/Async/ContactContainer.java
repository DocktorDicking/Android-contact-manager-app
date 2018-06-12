package com.example.jvw91.udemyidinformation.database.Async;


import android.content.Context;

import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactContainer {

    HashMap<String, String> textList;
    ArrayList<Contact> testData;
    boolean havesTestData = false;
    DbHelper dbHelper;
    Context context;
    Mode mode;

    public enum Mode {
        SAVE, UPDATE, DELETE
    }

    /**
     * Constructor when writing data
     * @param textList
     * @param dbHelper
     * @param context
     */
    public ContactContainer(HashMap<String, String> textList, DbHelper dbHelper, Context context, Mode mode) {
        this.textList = textList;
        this.dbHelper = dbHelper;
        this.context = context;
        this.mode = mode;
    }

    /**
     * Constructor used when retrieving data
     * @param dbHelper
     * @param context
     */
    public ContactContainer(DbHelper dbHelper, Context context) {
        this.dbHelper = dbHelper;
        this.context = context;
    }

    /**
     * Contstructor used when retrieving data and apply testData
     * @param dbHelper
     * @param context
     * @param testData
     */
    public ContactContainer(DbHelper dbHelper, Context context, ArrayList<Contact> testData) {
        this.dbHelper = dbHelper;
        this.context = context;
        this.testData = testData;
        this.havesTestData = true;
    }


}

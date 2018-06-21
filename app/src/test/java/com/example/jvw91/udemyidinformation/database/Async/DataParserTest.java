package com.example.jvw91.udemyidinformation.database.Async;

import android.content.ContentValues;
import android.widget.EditText;

import com.example.jvw91.udemyidinformation.BuildConfig;
import com.example.jvw91.udemyidinformation.TestDataGen;
import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataParserTest {
    DataParser parser = new DataParser();
    EditText cname, fname, lname, phone, mail;
    Contact contact;
    HashMap<String, String> list;

    @Before
    public void setUp(){
        contact = new TestDataGen().testContact();
        cname = new EditText(RuntimeEnvironment.application);
        fname = new EditText(RuntimeEnvironment.application);
        lname = new EditText(RuntimeEnvironment.application);
        phone = new EditText(RuntimeEnvironment.application);
        mail = new EditText(RuntimeEnvironment.application);
    }

    @Test
    public void contactToMap() {
        list = parser.parseToMap(contact);
        assertEquals(contact.getcName(), list.get("cName"));
        assertEquals(contact.getfName(), list.get("fName"));
        assertEquals(contact.getlName(), list.get("lName"));
    }

    @Test
    public void editTextToMap() {
        cname.setText(contact.getcName());
        fname.setText(contact.getfName());
        lname.setText(contact.getlName());
        phone.setText(contact.getPhoneNum());
        mail.setText(contact.getMail());

        EditText tst = Mockito.mock(EditText.class);

        list = parser.parseToMap(cname,fname,lname, phone, mail);
        assertEquals(contact.getcName(), list.get("cName"));
        assertEquals(contact.getfName(), list.get("fName"));
        assertEquals(contact.getlName(), list.get("lName"));
    }

    @Test
    public void mapToContact() {
        list = parser.parseToMap(cname,fname,lname, phone, mail);
        contact = parser.parseToContact(list);
        assertEquals(cname.getText().toString(), list.get("cName"));
        assertEquals(fname.getText().toString(), list.get("fName"));
        assertEquals(lname.getText().toString(), list.get("lName"));
    }

    @Test
    public void contactToContentValues() {
        ContentValues values;
        values = parser.parseToContentValues(contact);

        assertEquals(contact.getcName(), values.get(DbHelper.DB_Contract.Contact.COLUMN_CNAME));
        assertEquals(contact.getfName(), values.get(DbHelper.DB_Contract.Contact.COLUMN_FNAME));
        assertEquals(contact.getlName(), values.get(DbHelper.DB_Contract.Contact.COLUMN_LNAME));
    }
}
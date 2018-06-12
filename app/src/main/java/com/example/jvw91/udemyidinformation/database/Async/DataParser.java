package com.example.jvw91.udemyidinformation.database.Async;

import android.content.ContentValues;
import android.widget.EditText;

import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.HashMap;

/**
 * Collection of methods to parse data into collections.
 * To keep code more readable.
 */
public class DataParser {

    /**
     * Creates a HashMap<String, String> from EditText fields used in the app.
     * DRY
     * @param cName
     * @param fName
     * @param lName
     * @param phoneNum
     * @param mail
     * @return
     */
    public HashMap<String, String> parseToMap(EditText cName, EditText fName, EditText lName, EditText phoneNum, EditText mail) {
        HashMap<String, String> textMap = new HashMap<>();
        textMap.put("cName", cName.getText().toString());
        textMap.put("fName", fName.getText().toString());
        textMap.put("lName", lName.getText().toString());
        textMap.put("phoneNum", phoneNum.getText().toString());
        textMap.put("mail", mail.getText().toString());
        return textMap;
    }

    public HashMap<String, String> parseToMap(Contact contact) {
        HashMap<String, String> textMap = new HashMap<>();
        textMap.put("cName", contact.getcName());
        textMap.put("fName", contact.getfName());
        textMap.put("lName", contact.getlName());
        textMap.put("phoneNum", contact.getPhoneNum());
        textMap.put("mail", contact.getMail());
        textMap.put("id", contact.getId());
        return textMap;
    }

    public Contact parseToContact(HashMap<String, String> textMap) {
        //TODO: Replace with reflection loop.
        Contact contact = new Contact();
        if (textMap.containsKey("cName")) {contact.setcName(textMap.get("cName").trim());}
        if (textMap.containsKey("fName")) {contact.setfName(textMap.get("fName").trim());}
        if (textMap.containsKey("lName")) {contact.setlName(textMap.get("lName").trim());}
        if (textMap.containsKey("phoneNum")) {contact.setPhoneNum(textMap.get("phoneNum").trim());}
        if (textMap.containsKey("mail")) {contact.setMail(textMap.get("mail").trim());}
        if (textMap.containsKey("id")) {contact.setId(textMap.get("id").trim());}
        return contact;
    }

    public ContentValues parseToContentValues(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.DB_Contract.Contact.COLUMN_CNAME, contact.getcName());
        values.put(DbHelper.DB_Contract.Contact.COLUMN_FNAME, contact.getfName());
        values.put(DbHelper.DB_Contract.Contact.COLUMN_LNAME, contact.getlName());
        values.put(DbHelper.DB_Contract.Contact.COLUMN_PHONE, contact.getPhoneNum());
        values.put(DbHelper.DB_Contract.Contact.COLUMN_EMAIL, contact.getMail());
        return values;
    }
}

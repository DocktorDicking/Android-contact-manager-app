package com.example.jvw91.udemyidinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.jvw91.udemyidinformation.database.Async.ContactContainer;
import com.example.jvw91.udemyidinformation.database.Async.ContactRunner;
import com.example.jvw91.udemyidinformation.database.Async.DataParser;
import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.HashMap;

public class ContactList_detail extends AppCompatActivity {

    private Contact contact;
    EditText cName, fName, lName, phoneNum, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_detail);

        Intent intent = getIntent();
        this.contact = (Contact) intent.getSerializableExtra("contact");

        //Bind EditText to layout
        this.cName = findViewById(R.id.detail_cName);
        this.fName = findViewById(R.id.detail_fName);
        this.lName = findViewById(R.id.detail_lName);
        this.phoneNum = findViewById(R.id.detail_tel);
        this.mail = findViewById(R.id.detail_mail);

        //Set EditTextFields to existing data
        this.cName.setText(contact.getcName());
        this.fName.setText(contact.getfName());
        this.lName.setText(contact.getlName());
        this.phoneNum.setText(contact.getPhoneNum());
        this.mail.setText(contact.getMail());
    }

    public void btnUpdate(View v) {
        Log.e("Details:", "Update data");
        runTask(ContactContainer.Mode.UPDATE);
    }

    public void btnDelete(View v) {
        Log.e("Details:", "Delete data");
        runTask(ContactContainer.Mode.DELETE);
    }

    private void runTask(ContactContainer.Mode mode) {
        DbHelper db = new DbHelper(getApplicationContext());
        HashMap<String, String> textMap = new HashMap<>();
        if (mode == ContactContainer.Mode.UPDATE) {
            textMap = new DataParser().parseToMap(cName,fName,lName,phoneNum,mail);
            textMap.put("id", contact.getId());
        } else if (mode == ContactContainer.Mode.DELETE){
            textMap = new DataParser().parseToMap(this.contact);
        }

        ContactContainer params = new ContactContainer(textMap, db, getApplicationContext(), mode);
        ContactRunner cRunner = new ContactRunner();
        cRunner.execute(params);
        finish(); //Ends lifecycle of current view. Returns user to previous one.
    }
}

package com.example.jvw91.udemyidinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.jvw91.udemyidinformation.database.Async.ContactContainer;
import com.example.jvw91.udemyidinformation.database.Async.ContactRunner;
import com.example.jvw91.udemyidinformation.database.Async.DataParser;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText cName, fName, lName, phoneNum, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binds editTexts to layout
        this.cName = findViewById(R.id.id_cName);
        this.fName = findViewById(R.id.id_fName);
        this.lName = findViewById(R.id.id_lName);
        this.phoneNum = findViewById(R.id.id_tel);
        this.mail = findViewById(R.id.id_mail);
    }

    /**
     * Saves contact and clears EditText fields in layout.
     * onClick is handled by Android. See layout: android:onClick=""
     * @param v
     */
    public void btnSave(View v) {
        DbHelper db = new DbHelper(this);
        HashMap<String, String> textMap =
                new DataParser().parseToMap(this.cName, this.fName, this.lName, this.phoneNum, this.mail);

        ContactContainer params = new ContactContainer(textMap, db, getApplicationContext(), ContactContainer.Mode.SAVE);
        ContactRunner cRunner = new ContactRunner();
        cRunner.execute(params);

        cName.setText("");
        fName.setText("");
        lName.setText("");
        phoneNum.setText("");
        mail.setText("");
    }

    public void shwContacts(View v) {
        startActivity(new Intent(this, ContactList.class));
    }

}

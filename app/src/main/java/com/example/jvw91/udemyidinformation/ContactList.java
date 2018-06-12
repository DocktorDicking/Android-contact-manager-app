package com.example.jvw91.udemyidinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jvw91.udemyidinformation.adapter.ContactAdapter;
import com.example.jvw91.udemyidinformation.database.Async.ContactContainer;
import com.example.jvw91.udemyidinformation.database.Async.ContactLoader;
import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ContactList extends AppCompatActivity {
    private ArrayList<Contact> contacts;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Binds layout RV
        recyclerView = findViewById(R.id.recycler_view);

        ContactContainer params = new ContactContainer(db, getApplicationContext());
        ContactLoader contactLoader = new ContactLoader();
        contactLoader.execute(params);

        //Gets data from AsyncTask
        try {
            contacts = contactLoader.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Adapter contains logic to fill RV
        contactAdapter = new ContactAdapter(contacts, getApplicationContext());
        //LM is responsible for measuring and positioning items.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //Defines the animations that take place on items
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    /**
     * Runs when user gets back to this view from details.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}

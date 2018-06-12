package com.example.jvw91.udemyidinformation.database.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jvw91.udemyidinformation.R;
import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.ArrayList;

/**
 * AsyncTask to retrieve data.
 */
public class ContactLoader extends AsyncTask<ContactContainer, Integer, ArrayList> {

    //TODO: fix possible memory leak
    private Context context;

    @Override
    protected ArrayList doInBackground(ContactContainer... contactContainers) {
        DbHelper db = contactContainers[0].dbHelper;
        this.context = contactContainers[0].context;
        ArrayList<Contact> contacts;
        contacts = db.getContacts();
        if (contactContainers[0].havesTestData) {
            contacts.addAll(contactContainers[0].testData);
        }
        return contacts;
    }

    @Override
    protected void onPostExecute(ArrayList contacts) {
        String toastText = "ContactRunner Toast Error";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_SHORT);
        if (contacts.size() > 0) {
            toast.setText(R.string.tst_contactListFilled);
        } else {
            toast.setText(R.string.tst_contactListEmpty);
        }
        toast.show();
        super.onPostExecute(contacts);
    }
}

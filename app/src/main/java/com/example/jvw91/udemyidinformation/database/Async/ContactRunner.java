package com.example.jvw91.udemyidinformation.database.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jvw91.udemyidinformation.R;
import com.example.jvw91.udemyidinformation.database.Contact;
import com.example.jvw91.udemyidinformation.database.DbHelper;

import java.util.HashMap;

/**
 * AsyncTaskRunner to save a contact.
 */
public class ContactRunner extends AsyncTask<ContactContainer, Integer, Integer> {

    //TODO: Fix possible memory leak
    private Context context;
    private String toastText = "ContactRunner Toast Error";

    /**
     * Method runs in background.
     * Will handle data and save it to the database if possible.
     *
     * It was not possible to send an Object to the AsyncTask doInBackGround.
     * @param contactContainers
     * @return
     */
    @Override
    protected Integer doInBackground(ContactContainer... contactContainers) {
        DbHelper db = contactContainers[0].dbHelper;
        HashMap<String, String> textMap = contactContainers[0].textList;
        this.context = contactContainers[0].context;

        //Uses correct method based on mode.
        switch (contactContainers[0].mode) {
            case SAVE:
                if (saveContact(db, textMap) == 1) {
                    this.toastText = context.getString(R.string.tst_savingContactSuccess);
                    return 1;
                } else {
                    this.toastText = context.getString(R.string.tst_savingContactFailed);
                    return 0;
                }
            case UPDATE:
                if (updateContact(db, textMap) == 1) {
                    this.toastText = context.getString(R.string.tst_updatedContactSuccess);
                    return 1;
                } else {
                    this.toastText = context.getString(R.string.tst_updatedContactFailed);
                    return 0;
                }
            case DELETE:
                if (deleteContact(db, textMap) == 1) {
                    this.toastText = context.getString(R.string.tst_deletedContactSuccess);
                    return 1;
                } else {
                    this.toastText = context.getString(R.string.tst_deletedContactFailed);
                    return 0;
                }
            default:
                return 0; //OMG mode is not valid!!!
        }
    }

    /**
     * Returns Toast as feedback for the user.
     * Finishes the AsyncTask
     * @param integer
     */
    @Override
    protected void onPostExecute(Integer integer) {
        Toast toast = Toast.makeText(context, this.toastText, Toast.LENGTH_SHORT);
        toast.show();
        super.onPostExecute(integer);
    }

    //Methods used in mode.
    private Integer saveContact(DbHelper db, HashMap<String, String> textMap) {
        Contact contact = new DataParser().parseToContact(textMap);
        if (db.storeContact(contact)){
            return 1;
        }
        return 0;
    }

    private Integer deleteContact(DbHelper db, HashMap<String, String> textMap) {
        Contact contact = new DataParser().parseToContact(textMap);
        if (db.deleteContact(contact)) {
            return 1;
        }
        return 0;
    }

    private Integer updateContact(DbHelper db, HashMap<String, String> textMap) {
        Contact contact = new DataParser().parseToContact(textMap);
        if (db.updateContact(contact)) {
            return 1;
        }
        return 0;
    }
}


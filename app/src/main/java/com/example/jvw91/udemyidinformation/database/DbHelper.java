package com.example.jvw91.udemyidinformation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.example.jvw91.udemyidinformation.database.Async.DataParser;

import java.util.ArrayList;

/**
 * Helper class. Holds all DB functionality and db contracts.
 * Move db contracts to separate class when it grows.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "businessCard.db";
    private static final int VERSION = 1;
    private static final long SQLERROR = -1;
    private static final long SQLNOCHANGE = 0;
    private Context context;

    /**
     * Default constructor
     * @param context
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    /**
     * Runs on first startup when database is created. Or when the database is updated.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DbHelper: ", DB_Contract.Contact.CREATE_TABLE);
        db.execSQL(DB_Contract.Contact.CREATE_TABLE);
    }

    /**
     * Only runs when database VERSION changes. Drops old database and inits a newone.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Add code to keep data.
        Log.e("DbHelper: ", DB_Contract.Contact.DROP_TABLE);
        db.execSQL(DB_Contract.Contact.DROP_TABLE);
        onCreate(db);
    }

    /**
     * For testing. Clears the database.
     */
    public void clearDbAndRecreate() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DB_Contract.Contact.DROP_TABLE);
        db.execSQL(DB_Contract.Contact.CREATE_TABLE);
    }
    /**
     * Stores a contact object to the database. Creates Toasts to notify user.
     * @param contact
     * @return success
     */
    public boolean storeContact(Contact contact) {
        boolean success = false;
        SQLiteDatabase db = getWritableDatabase();

        if (!contact.isEmpty()) {
            ContentValues values = new DataParser().parseToContentValues(contact);

            long sql = db.insert(DB_Contract.Contact.TABLE_NAME, null, values);

            if (sql != SQLERROR && sql != SQLNOCHANGE) {
                success = true;
            }
        }
        db.close();
        return success;
    }

    public boolean updateContact(Contact contact) {
        boolean success = false;
        SQLiteDatabase db = getWritableDatabase();

        if (!contact.isEmpty()) {
            ContentValues values = new DataParser().parseToContentValues(contact);
            
            long sql = db.update(DB_Contract.Contact.TABLE_NAME, values, "id=?", new String[]{contact.getId()});

            if (sql != SQLERROR && sql != SQLNOCHANGE) {
                success = true;
            }
        }
        return success;
    }

    public boolean deleteContact(Contact contact) {
        boolean success = false;
        SQLiteDatabase db = getWritableDatabase();

        if (!contact.isEmpty()) {
            Log.e("DELETE: ", "id=" + contact.getId());
            long sql = db.delete(DB_Contract.Contact.TABLE_NAME,"id=?", new String[]{contact.getId()});

            if (sql != SQLERROR && sql != SQLNOCHANGE) {
                success = true;
            }
        }
        return success;
    }

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + DB_Contract.Contact.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            contacts.add(new Contact(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            ));
        }
        cursor.close(); //Clears cursor
        db.close();
        return contacts;
    }

    /**
     * Nested contract class.
     * This contract sets the Database structure.
     */
    public static class DB_Contract {

        public static class Contact implements BaseColumns {
            //Table name
            public static final String TABLE_NAME = "contact";

            //Columns
            public static final String COLUMN_ID = "id";
            public static final String COLUMN_CNAME = "company_name";
            public static final String COLUMN_FNAME = "first_name";
            public static final String COLUMN_LNAME = "last_name";
            public static final String COLUMN_PHONE = "phone_number";
            public static final String COLUMN_EMAIL = "email";

            //Create table sql
            static final String CREATE_TABLE = "CREATE TABLE " + Contact.TABLE_NAME + "("
                    + Contact.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Contact.COLUMN_CNAME + " TEXT, "
                    + Contact.COLUMN_FNAME + " TEXT, "
                    + Contact.COLUMN_LNAME + " TEXT, "
                    + Contact.COLUMN_PHONE + " TEXT, "
                    + Contact.COLUMN_EMAIL + " TEXT) ";

            static final String DROP_TABLE = "DROP TABLE IF EXISTS " + Contact.TABLE_NAME;
        }

    }
}

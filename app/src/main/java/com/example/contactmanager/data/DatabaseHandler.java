package com.example.contactmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;
import com.example.contactmanager.util.Util;

import java.util.ArrayList;
import java.util.List;

import static com.example.contactmanager.util.Util.KEY_ID;
import static com.example.contactmanager.util.Util.KEY_PHONE_NUMBER;
import static com.example.contactmanager.util.Util.TABLE_NAME;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String CREATE_CONTACT_TABLE = "CREATE TABLE "+Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY, "
                + Util.KEY_NAME + " TEXT, "
                + Util.KEY_PHONE_NUMBER + " TEXT)";
        db.execSQL(CREATE_CONTACT_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = String.valueOf(R.string.db_drop);
        db.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Util.KEY_ID, contact.getId());
        values.put(Util.KEY_NAME, contact.getName());
        //values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber(), values);
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.insert(Util.TABLE_NAME, null, values);
        Log.d("test", "addContact: "+"item added");
        db.close();

    }

    // not tested
    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        //        db.delete(TABLE_NAME, KEY_ID + "=" + new String[]{String.valueOf(contact.getId())}, null);
        db.close();
    }

    // not tested
    public boolean updateContact(Contact contact){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[]{String.valueOf(contact.getId())});

        return true;
    }

    public Contact getContact(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, KEY_PHONE_NUMBER},

            Util.KEY_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
            Contact contact = new Contact();

            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setPhoneNumber(cursor.getString(2));

            return contact;
        }else{
            return null;
        }

    }

    //get all contacts
    public List<Contact> contactList = new ArrayList<>();

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM "+ Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {

            do{
                Contact contact = new Contact();

                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        db.close();
        return contactList;
    }

}

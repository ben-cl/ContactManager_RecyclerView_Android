package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.contactmanager.adapter.RecyclerViewAdapter;
import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private ListView listView;
    private ArrayList<Contact> contacttArrayList;
    //private ArrayList<String> contacttArrayList;
    private RecyclerView recycleView;
    public RecyclerViewAdapter recycleviewAdapter;
    //private ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contacttArrayList = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        //SQLiteDatabase db = this.getWritableDatabase();

        recycleView = findViewById(R.id.recyclerview);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //db.addContact(new Contact("Alie", "5145144444"));
        //db.addContact(new Contact("Marie", "5145144444"));

        List<Contact> contactList = db.getAllContacts();

        for(Contact contact: contactList) {
            Log.d("test list", "onCreate: " + contact.getName());
            Log.d("test list", "onCreate: " + contact.getPhoneNumber());
            Log.d("test list", "onCreate: " + contact.getId());

            contacttArrayList.add(contact);


        }
        recycleviewAdapter = new RecyclerViewAdapter(this, contacttArrayList);
        recycleView.setAdapter(recycleviewAdapter);

//        db.addContact(new Contact("Alie", "5145144444"));
//        db.addContact(new Contact("Marie", "5145144444"));
//        db.addContact(new Contact("Chanelle", "5145144444"));

        //listView = findViewById((R.id.listview));


            //contacttArrayList.akdd(contact.getName());
            //Log.d("test list", "onCreate: "+contact.getId());
        }



//        //db.onCreate(db);
//        Contact diane = new Contact();
//
//        //diane.setId(1);
//        diane.setName("Diane");
//        diane.setPhoneNumber("5551234567");
        
//        db.addContact(diane);
//
//        db.addContact(new Contact("Alie", "5145144444"));
//        db.addContact(new Contact("Marie", "5145144444"));
//        db.addContact(new Contact("Chanelle", "5145144444"));
//        db.addContact(new Contact("Ben", "5145144444"));
//        db.addContact(new Contact("Isaac", "5145144444"));
//        db.addContact(new Contact("Joe", "5145144444"));
//        db.addContact(new Contact("Georgie", "5145144444"));
//        db.addContact(new Contact("Ivan", "5145144444"));
//        db.addContact(new Contact("Newton", "5145144444"));
//        db.addContact(new Contact("Apolo", "5145144444"));



//
//        Contact dianeSingle = db.getContact(1);
//
//        //diane.setId(1);
//        dianeSingle.setName("DianeNew");
//        db.updateContact(dianeSingle);

        //db.addContact(diane);


    }


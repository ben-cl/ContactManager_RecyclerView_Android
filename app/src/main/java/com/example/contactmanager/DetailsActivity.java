package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.contactmanager.data.DatabaseHandler;
import com.example.contactmanager.model.Contact;

public class DetailsActivity extends AppCompatActivity {

    private TextView nameTv;
    private TextView phoneTv;

    private String name2;
    private String phone;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameTv = findViewById(R.id.name);
        phoneTv = findViewById(R.id.phone);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String name = bundle.getString("name");
            String number = bundle.getString("phone");

            nameTv.setText(name);
            phoneTv.setText(number);
        }
        DatabaseHandler db = new DatabaseHandler(DetailsActivity.this);


    }
}

package com.example.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.DetailsActivity;
import com.example.contactmanager.R;
import com.example.contactmanager.model.Contact;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    //<String> contactList)
    public RecyclerViewAdapter(Context context,  List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Contact contact = contactList.get(position);

        holder.name.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());


    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView phoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone_number);


        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Contact contact = contactList.get(position);
            Log.d("Clicked", "onClick: "+position);

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name",contact.getName());
            intent.putExtra("phone", contact.getPhoneNumber());

            context.startActivity(intent);
        }
    }
}

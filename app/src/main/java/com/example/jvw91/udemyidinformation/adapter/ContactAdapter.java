package com.example.jvw91.udemyidinformation.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jvw91.udemyidinformation.ContactList_detail;
import com.example.jvw91.udemyidinformation.R;
import com.example.jvw91.udemyidinformation.database.Contact;

import java.util.ArrayList;

/**
 * Contact recycler view adapter. Populates the recycler view with data.
 * Uses contact_rlist_row.xml as layout model, which will populate the view.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private ArrayList<Contact> contacts;
    private Context context;

    /**
     * Default constructor
     * @param contacts
     */
    public ContactAdapter(ArrayList<Contact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    /**
     * Runs when ViewHolder is created. Binds ViewHolder to row Layout.
     * @param parent
     * @param viewType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rlist_row,
                parent, false);

        return new ViewHolder(itemView);
    }

    /**
     * Sets each contact in ArrayList to a ViewHolder. Used by parent class.
     * Listens for clicks. When an item in the list is clicked a new activity will be launched.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contact contact = contacts.get(position);
        holder.cName.setText(contact.getcName());
        holder.fName.setText(contact.getfName());
        holder.lName.setText(contact.getlName());
        holder.phoneNum.setText(contact.getPhoneNum());
        holder.mail.setText(contact.getMail());

        //Click listener for each row in the list.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked item: " + contact.getcName(), Toast.LENGTH_SHORT).show();

                //Create a new intent and add Contact to it as a 'SerializableExtra'
                Intent intent = new Intent(context, ContactList_detail.class); //TODO: Add activity.
                intent.putExtra("contact", contact);
                context.startActivity(intent);
            }
        });
    }

    /**
     * Returns List size, used by parent class to determine how many items it needs to bind.
     * @return
     */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    /**
     * ViewHolder class defines ViewHolder Object.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cName, fName, lName, phoneNum, mail;

        public ViewHolder(View view) {
            super(view);
            this.cName = view.findViewById(R.id.row_cName);
            this.fName = view.findViewById(R.id.row_fName);
            this.lName = view.findViewById(R.id.row_lName);
            this.phoneNum = view.findViewById(R.id.row_phone);
            this.mail = view.findViewById(R.id.row_email);
        }
    }

}

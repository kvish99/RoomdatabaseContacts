package com.example.roomdatabaseexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabaseexample.databinding.ContactlistBinding;
import com.example.roomdatabaseexample.model.Contacts;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contacts;
    public Contacts contact;
    private OnContactClickListener onClickListener;

    public MyAdapter(ArrayList<Contacts> contacts, OnContactClickListener onClickListener) {
        this.contacts = contacts;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creating new View holders for items in recyclerView


        ContactlistBinding contactListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contactlist,
                        parent,
                        false
                );

        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // Called by recyclerView when it needs to display or update an item
        // at a specific position in the list or grid.

        Contacts currentContact = contacts.get(position);

        holder.contactlistBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
        // Determines the total number of items in the dataset that will
        // be displayed in the recyclerview

        if (contacts != null) {
            return contacts.size();
        } else {
            return 0;
        }
    }


    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        // Inform the associated RecyclerView that the underlying
        // dataset has changed, and the RecyclerView should refresh
        // its views to reflect these changes.
        notifyDataSetChanged();

    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private ContactlistBinding contactlistBinding;

        public ContactViewHolder(@NonNull ContactlistBinding contactlistBinding) {
            super(contactlistBinding.getRoot());
            this.contactlistBinding = contactlistBinding;
            contactlistBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        int position1 = getAdapterPosition();
                        if (position1 != RecyclerView.NO_POSITION) {
                            onClickListener.OnItemClick(position1);
                        }
                    }
                }
            });
        }
    }
}
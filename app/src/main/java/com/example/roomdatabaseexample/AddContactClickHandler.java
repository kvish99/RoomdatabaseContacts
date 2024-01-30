package com.example.roomdatabaseexample;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabaseexample.model.Contacts;
import com.example.roomdatabaseexample.viewmodel.ContactViewModel;

public class AddContactClickHandler {

    Contacts contact;
    Context context;
    ContactViewModel myViewModel;

    public AddContactClickHandler(Contacts contact, Context context, ContactViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;

    }

    public void onSubmitBtnClicked(View view) {
        if (contact.getName() == null || contact.getEmail() == null) {
            Toast.makeText(context, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);

            Contacts c = new Contacts(
                    contact.getName(),
                    contact.getEmail()
            );

            myViewModel.addNewContact(c);

            context.startActivity(i);

        }
    }
}
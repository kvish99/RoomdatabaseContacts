package com.example.roomdatabaseexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.roomdatabaseexample.databinding.ActivityAddContactBinding;
import com.example.roomdatabaseexample.model.Contacts;
import com.example.roomdatabaseexample.viewmodel.ContactViewModel;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding activityAddContactBinding;
    private AddContactClickHandler handler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        contacts = new Contacts();

        activityAddContactBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_contact
        );


        // VIew Model
        ContactViewModel contactViewModel = new ViewModelProvider(this)
                .get(ContactViewModel.class);


        handler = new AddContactClickHandler(
                contacts,
                this,
                contactViewModel
        );

        activityAddContactBinding.setContact(contacts);
        activityAddContactBinding.setClickHandler(handler);

    }

}
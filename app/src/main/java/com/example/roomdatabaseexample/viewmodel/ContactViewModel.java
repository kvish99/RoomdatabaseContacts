package com.example.roomdatabaseexample.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.roomdatabaseexample.model.Contacts;
import com.example.roomdatabaseexample.repository.ContactRepository;
import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository myRepository;

    // LiveData
    private LiveData<List<Contacts>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new ContactRepository(application);
        this.allContacts = myRepository.getAllContacts(); // Initialize LiveData
    }

    // Getter method for LiveData


    public void addNewContact(Contacts contact) {

        myRepository.addContact(contact);
    }

    public void updateContact(Contacts contacts) {

        myRepository.updateContact(contacts);
    }

    public void deleteContact(Contacts contacts) {

        myRepository.deleteContact(contacts);
    }

    public LiveData<List<Contacts>> getAllContacts() {

        return allContacts;
    }
}
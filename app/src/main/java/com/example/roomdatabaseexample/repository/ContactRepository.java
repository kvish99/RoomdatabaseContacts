package com.example.roomdatabaseexample.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import com.example.roomdatabaseexample.Dao.ContactDAO;
import com.example.roomdatabaseexample.model.Contacts;
import com.example.roomdatabaseexample.roomdatabase.ContactDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactRepository {
    private final ContactDAO contactDao;
    private final ExecutorService executorService;
    private final Handler handler;

    public ContactRepository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDao = contactDatabase.getContactDAO();

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contacts) {
        executorService.execute(() -> {
            contactDao.insert(contacts);
        });
    }

    public void deleteContact(Contacts contacts) {
        executorService.execute(() -> {
            contactDao.delete(contacts);
        });
    }

    public void updateContact(Contacts contacts) {
        executorService.execute(() -> {
            contactDao.update(contacts);
        });
    }

    public LiveData<List<Contacts>> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
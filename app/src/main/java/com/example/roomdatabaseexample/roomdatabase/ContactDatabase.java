package com.example.roomdatabaseexample.roomdatabase;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabaseexample.Dao.ContactDAO;
import com.example.roomdatabaseexample.model.Contacts;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {


    public abstract ContactDAO getContactDAO();
    private static ContactDatabase dbInstance;


    public static synchronized ContactDatabase getInstance(Application context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ContactDatabase.class,
                            "contacts_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
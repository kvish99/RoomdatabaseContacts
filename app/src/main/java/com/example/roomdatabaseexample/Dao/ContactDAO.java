package com.example.roomdatabaseexample.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabaseexample.model.Contacts;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    void insert(Contacts contacts);

    @Delete
    void delete(Contacts contacts);

    @Update
    void update(Contacts contacts);

    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getAllContacts();
}

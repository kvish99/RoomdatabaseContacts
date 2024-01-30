package com.example.roomdatabaseexample.model;



import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.os.Bundle;

@Entity(tableName = "contacts_table")
public class Contacts {

    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;

    public Contacts( String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Contacts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
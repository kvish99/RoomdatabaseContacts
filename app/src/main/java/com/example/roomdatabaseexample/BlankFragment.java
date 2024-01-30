package com.example.roomdatabaseexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomdatabaseexample.databinding.FragmentBlankBinding;
import com.example.roomdatabaseexample.model.Contacts;
import com.example.roomdatabaseexample.viewmodel.ContactViewModel;

public class BlankFragment extends DialogFragment {

    Contacts contacts;
    ContactViewModel contactViewModel;
    Context context;



    public BlankFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentBlankBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
       contacts = new Contacts();

        if (getArguments() != null) {
            String a1 = getArguments().getString("name");
            String a2 = getArguments().getString("email");
            String a3 = String.valueOf(getArguments().getInt("id", -1));
            contacts.setName(a1);
            contacts.setEmail(a2);
            contacts.setId(Integer.parseInt(a3));
            binding.setContact(contacts);


        }
        binding.submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contacts.getName().isEmpty() || contacts.getEmail().isEmpty()) {
                    Toast.makeText(getContext(), "Please fill the Above fields ", Toast.LENGTH_SHORT).show();
                } else {
                    if (contactViewModel != null) {
                        contactViewModel.updateContact(contacts);
                        Intent intent = new Intent(requireActivity().getApplication(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Data is not Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity().getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }


}
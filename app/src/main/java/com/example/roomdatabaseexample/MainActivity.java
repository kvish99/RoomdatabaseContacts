package com.example.roomdatabaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.roomdatabaseexample.databinding.ActivityMainBinding;
import com.example.roomdatabaseexample.model.Contacts;
import com.example.roomdatabaseexample.roomdatabase.ContactDatabase;
import com.example.roomdatabaseexample.viewmodel.ContactViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnContactClickListener {

    ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    // Adapter
    private MyAdapter myAdapter;

    // Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandler;

    // RecyclerView
    private RecyclerView recyclerView;

    // View model
    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityClickHandler = new MainActivityClickHandlers(this);
        activityMainBinding.setClickHandler(mainActivityClickHandler);

        // RecyclerView
        recyclerView = activityMainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        contactDatabase = ContactDatabase.getInstance(this.getApplication());

        // View Model
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // Loading the Data from ROOM DB
        contactViewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contactsArrayList.clear();

                for (Contacts c : contacts)
                    contactsArrayList.add(c);
                myAdapter.notifyDataSetChanged();
            }
        });

        // Adapter
        myAdapter = new MyAdapter(contactsArrayList, this);
        recyclerView.setAdapter(myAdapter);

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // If you swipe the item to the left
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                contactViewModel.deleteContact(c);

                Snackbar snackbar = Snackbar.make(activityMainBinding.getRoot(), "Contact Deleted", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contactViewModel.addNewContact(c);
                    }
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }


    @Override
    public void OnItemClick(int position) {
        BlankFragment dialogFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",contactsArrayList.get(position).getName());
        bundle.putString("email",contactsArrayList.get(position).getEmail());
        bundle.putInt("id",contactsArrayList.get(position).getId());
        dialogFragment.setArguments(bundle);

        // Show the dialog
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogFragment.show(fragmentManager, "BlankFragment");
    }
}

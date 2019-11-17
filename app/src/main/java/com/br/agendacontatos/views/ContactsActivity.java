package com.br.agendacontatos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.br.agendacontatos.R;
import com.br.agendacontatos.adapters.ContactAdapter;
import com.br.agendacontatos.models.ContactModel;
import com.br.agendacontatos.viewmodels.ContactsViewModel;

import java.util.List;

import javax.annotation.Nullable;

public class ContactsActivity extends AppCompatActivity {

    private ContactsViewModel contactsViewModel;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        phone = getIntent().getStringExtra("phone");

        recyclerView = findViewById(R.id.recycler_view);

        this.contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);

        contactsViewModel.initContacts(phone);

        contactsViewModel.getContacts().observe(this, new Observer<List<ContactModel>>() {
            @Override
            public void onChanged(@Nullable List<ContactModel> contactModels) {
            contactAdapter.notifyData(contactsViewModel.getContacts().getValue());
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        contactAdapter = new ContactAdapter(this, contactsViewModel.getContacts().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

}

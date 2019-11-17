package com.br.agendacontatos.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.br.agendacontatos.R;
import com.br.agendacontatos.models.ContactModel;

public class ContactDetailActivity extends AppCompatActivity {

    private ContactModel contact;

    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contact = (ContactModel) getIntent().getSerializableExtra("contact");

        nameTextView = findViewById(R.id.text_name);
        phoneTextView = findViewById(R.id.text_phone);
        addressTextView = findViewById(R.id.text_address);

        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhone());
        addressTextView.setText(contact.getAddress());


    }
}

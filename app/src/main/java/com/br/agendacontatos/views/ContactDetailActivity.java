package com.br.agendacontatos.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.br.agendacontatos.R;
import com.br.agendacontatos.models.ContactModel;

public class ContactDetailActivity extends AppCompatActivity {

    private ContactModel contact;

    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView addressTextView;
    private Button gochatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        contact = (ContactModel) getIntent().getSerializableExtra("contact");

        nameTextView = findViewById(R.id.text_name);
        phoneTextView = findViewById(R.id.text_phone);
        addressTextView = findViewById(R.id.text_address);
        gochatButton = findViewById(R.id.button_gochat);

        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhone());
        addressTextView.setText(contact.getAddress());

        gochatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ChatActivity.class);

                i.putExtra("contactDetails", contact);

                startActivity(i);
            }
        });


    }
}

package com.br.agendacontatos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.agendacontatos.R;
import com.br.agendacontatos.adapters.ChatAdapter;
import com.br.agendacontatos.models.ContactModel;
import com.br.agendacontatos.models.MessageModel;
import com.br.agendacontatos.repositories.LoginRepository;
import com.br.agendacontatos.viewmodels.ChatViewModel;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ChatAdapter chatAdapter;
    private RecyclerView recyclerView;
    private ChatViewModel chatViewModel;

    private ContactModel contact;
    private String fromPhone;

    private EditText messageEditText;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        contact = (ContactModel) getIntent().getSerializableExtra("contactDetails");

        fromPhone = LoginRepository.phone;

        recyclerView = findViewById(R.id.recycler_view);

        messageEditText = findViewById(R.id.edit_message);
        sendMessageButton = findViewById(R.id.button_send_message);

        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);

        chatViewModel.initMessages(contact.getChatId());
        chatViewModel.getMessages().observe(this, new Observer<ArrayList<MessageModel>>() {
            @Override
            public void onChanged(ArrayList<MessageModel> messageModels) {
                Log.d("-----------", chatViewModel.getMessages().getValue().toString());
                chatAdapter.notifyData(chatViewModel.getMessages().getValue());
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageEditText.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Digite uma mensagem válida!", Toast.LENGTH_SHORT);
                }else {
                    chatViewModel.sendMessage(contact.getChatId(), contact.getPhone(), fromPhone, messageEditText.getText().toString());
                }
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        chatAdapter = new ChatAdapter(this, chatViewModel.getMessages().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);
    }
}

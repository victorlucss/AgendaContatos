package com.br.agendacontatos.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ChatRepository {

    private static ChatRepository instance;

    private FirebaseFirestore db;

    private ChatRepository(){
        db = FirebaseFirestore.getInstance();
    }

    public static ChatRepository getInstance(){
        if(instance == null) instance = new ChatRepository();

        return instance;
    }

    public Query getMessages(String chatId){
        return db.collection("messages")
                .whereEqualTo("chatId", chatId);
    }


    public void sendMessage(String chatId, String toPhone, String fromPhone, String message) {

        HashMap<String, Object> messageToSend = new HashMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = formatter.format(new Date());

        messageToSend.put("chatId", chatId);
        messageToSend.put("to", toPhone);
        messageToSend.put("from", fromPhone);
        messageToSend.put("message", message);
        messageToSend.put("date", date);

        db.collection("messages").add(messageToSend);

    }
}

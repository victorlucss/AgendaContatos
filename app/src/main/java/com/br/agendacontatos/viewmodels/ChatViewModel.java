package com.br.agendacontatos.viewmodels;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.br.agendacontatos.models.MessageModel;
import com.br.agendacontatos.repositories.ChatRepository;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ChatViewModel extends ViewModel {

    private ChatRepository repository = ChatRepository.getInstance();
    private MutableLiveData<ArrayList<MessageModel>> messages = new MutableLiveData<>();
    private Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy HH:mm:ss")
            .create();

    public void initMessages(String chatId){
        repository.getMessages(chatId)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        ArrayList<MessageModel> messageModels = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            JsonElement jsonElement = gson.toJsonTree(doc.getData());
                            MessageModel pojo = gson.fromJson(jsonElement, MessageModel.class);
                            messageModels.add(pojo);
                        }

                        Collections.sort(messageModels, new Comparator<MessageModel>() {
                            @Override
                            public int compare(MessageModel a, MessageModel b) {
                                if (a.getDate().getTime() < b.getDate().getTime())
                                    return -1;
                                else if (a.getDate().getTime() > b.getDate().getTime())
                                    return 1;
                                else
                                    return 0;
                            }
                        });

                        messages.postValue(messageModels);
                    }
                });
    }

    public LiveData<ArrayList<MessageModel>> getMessages(){
        return this.messages;
    }

    public void sendMessage(String chatId, String toPhone, String fromPhone, String message){

        repository.sendMessage(chatId, toPhone, fromPhone, message);

    }
}

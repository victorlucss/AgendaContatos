package com.br.agendacontatos.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.br.agendacontatos.models.ContactModel;
import com.br.agendacontatos.repositories.ContactRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactsViewModel extends ViewModel {

    private ContactRepository repository = ContactRepository.getInstance();
    private MutableLiveData<ArrayList<ContactModel>> contacts = new MutableLiveData<>();

    public LiveData<ArrayList<ContactModel>> getContacts(){
        return contacts;
    }



    public void initContacts(String phone){
        this.repository.getContactsFirebase(phone)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Gson gson = new Gson();

                            ArrayList<ContactModel> contactModels = new ArrayList<>();
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                JsonElement jsonElement = gson.toJsonTree(doc.getData());
                                ContactModel pojo = gson.fromJson(jsonElement, ContactModel.class);
                                contactModels.add(pojo);
                            }


                            Log.d(TAG, contactModels.toString());

                            contacts.postValue(contactModels);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }


}

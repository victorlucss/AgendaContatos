package com.br.agendacontatos.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.br.agendacontatos.models.ContactModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactRepository {

    private FirebaseFirestore mFirestore;
    private Gson gson = new Gson();

    private ArrayList<ContactModel> contacts = new ArrayList<>();

    private static ContactRepository instance;

    private ContactRepository() {
        this.mFirestore = FirebaseFirestore.getInstance();
    }

    public static ContactRepository getInstance(){
        if(instance == null) instance = new ContactRepository();

        return instance;
    }


    public Task<QuerySnapshot> getContactsFirebase(String phone){
        return this.mFirestore
                .collection("contacts")
                .whereEqualTo("owner", phone)
                .get();
    }


}

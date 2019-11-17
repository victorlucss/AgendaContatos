package com.br.agendacontatos.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.br.agendacontatos.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginRepository {

    private final static String TAG = "LoginREpository";

    private static LoginRepository instance;
    private FirebaseFirestore mFirestore;

    public static String phone;
    private UserModel currentUser;

    private LoginRepository() {
        this.mFirestore = FirebaseFirestore.getInstance();
    }

    public static LoginRepository getInstance() {
        if(instance == null) instance = new LoginRepository();

        return instance;
    }

    public UserModel getUser(){
        return currentUser;
    }

    public void login(final UserModel user) {
        DocumentReference docRef = this.mFirestore.collection("users").document(user.getPhone());

        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            phone = user.getPhone();

                            if(task.getResult().exists()) currentUser = user;
                            else {
                                Map<String, Object> phoneUser = new HashMap<>();
                                phoneUser.put("phone", user.getPhone());

                                mFirestore.collection("users").document(user.getPhone()).set(phoneUser);
                                currentUser = user;
                            }
                        }
                    }
                });
    }

}

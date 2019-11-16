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

    private String phone;
    private UserModel currentUser;

    private LoginRepository() {
        this.mFirestore = FirebaseFirestore.getInstance();
    }

    public static LoginRepository getInstance() {
        if(instance == null) instance = new LoginRepository();

        return instance;
    }

    public MutableLiveData<UserModel> getUser(){
        MutableLiveData<UserModel> user = new MutableLiveData<>();
        user.setValue(this.currentUser);

        return user;
    }

    public void login(final UserModel user) {
        DocumentReference docRef = this.mFirestore.collection("users").document(user.getPhone());

        Log.d(TAG, "Chamando doutor hans chucrute");

        docRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()) currentUser = user;
                            else {
                                Log.d(TAG, "Criando novo usuário");
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

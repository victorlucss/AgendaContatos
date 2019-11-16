package com.br.agendacontatos.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.br.agendacontatos.models.UserModel;
import com.br.agendacontatos.repositories.LoginRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<UserModel> currentUser = new MutableLiveData<>();

    private LoginRepository repository;

    public LiveData<UserModel> getCurrentUser() {
        return currentUser;
    }

    public void login(String phone) {

        UserModel user = new UserModel(phone);
        repository.login(user);

        this.currentUser.postValue(repository.getUser().getValue());
    }

    public void logout(){

    }


}

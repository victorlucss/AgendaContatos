package com.br.agendacontatos.models;

public class UserModel {

    private String phone;

    public UserModel(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

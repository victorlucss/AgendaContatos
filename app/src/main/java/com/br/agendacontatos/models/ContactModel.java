package com.br.agendacontatos.models;

import java.io.Serializable;

public class ContactModel implements Serializable {

    private String name;
    private String phone;
    private String address;
    private String chatId;

    public ContactModel(String name, String phone, String address, String chatId) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }
}

package com.br.agendacontatos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.agendacontatos.R;
import com.br.agendacontatos.models.UserModel;
import com.br.agendacontatos.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneEditText;
    private Button loginButton;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneEditText = findViewById(R.id.edit_phone);
        loginButton = findViewById(R.id.button_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getCurrentUser().observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                Toast.makeText(LoginActivity.this, "um teste bem realizado e funcional :D", Toast.LENGTH_SHORT).show();
            }
        });

        initListeners();
    }

    private void initListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(phoneEditText.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Digite um número de telefone válido!", Toast.LENGTH_LONG).show();
                    return;
                }

                loginViewModel.login(phoneEditText.getText().toString());

            }
        });
    }
}

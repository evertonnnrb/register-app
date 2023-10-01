package com.eitgh.registers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    TextView linkNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linkNewUser = findViewById(R.id.txtLinkNewUser);
        linkNewUser.setOnClickListener(e -> {
            _callFisrtAccess();
        });

        buttonLogin = findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(e -> {
            _callMain();
        });
    }

    public void _callMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Log.i("click", "btn login clicado");
    }

    public void _callFisrtAccess() {
        Intent intent = new Intent(this, FirstAccessActivity.class);
        startActivity(intent);
        Log.i("click", "btn login clicado");
    }
}
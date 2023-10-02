package com.eitgh.registers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eitgh.registers.entities.User;
import com.eitgh.registers.repository.UsersRepository;

public class LoginActivity extends AppCompatActivity {
    private Button buttonLogin;
    private EditText edtUserEmail, edtUserPassword;
    private TextView linkNewUser;
    private User user;
    private UsersRepository usersRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usersRepository = new UsersRepository(this);
        edtUserEmail = findViewById(R.id.loginEmail);
        edtUserPassword = findViewById(R.id.loginPasswd);

        linkNewUser = findViewById(R.id.txtLinkNewUser);
        linkNewUser.setOnClickListener(e -> {
            _callFisrtAccess();
        });

        buttonLogin = findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(e -> {
            if (login(edtUserEmail.getText().toString(), edtUserPassword.getText().toString())) {
                _callMain();
            } else {
                edtUserPassword.setText("");
                Toast.makeText(this, "USER OR EMAIL INVALID", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean login(String email, String password) {
        user = usersRepository.login(email, password);
        return user != null;
    }

    private void _callMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    private void _callFisrtAccess() {
        Intent intent = new Intent(this, FirstAccessActivity.class);
        startActivity(intent);
    }
}
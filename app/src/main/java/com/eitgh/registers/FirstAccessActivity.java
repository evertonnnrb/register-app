package com.eitgh.registers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eitgh.registers.entities.User;
import com.eitgh.registers.repository.UsersRepository;

public class FirstAccessActivity extends AppCompatActivity {

    private UsersRepository repository;

    private EditText edtUserName, edtUserEmail, edtUserPassword;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_access);

        repository = new UsersRepository(this);

        edtUserName = findViewById(R.id.edtUsername);
        edtUserEmail = findViewById(R.id.edtemail);
        edtUserPassword = findViewById(R.id.edtPassword);
        btnSave = findViewById(R.id.btnNewUser);

        btnSave.setOnClickListener(v -> {
            repository.saveUser(createUser());
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            _clear();
        });
    }

    private User createUser() {
        User user = new User();
        user.setName(edtUserName.getText().toString());
        user.setEmail(edtUserEmail.getText().toString());
        user.setPassword(edtUserPassword.getText().toString());
        return user;
    }

    private void _clear() {
        edtUserName.setText("");
        edtUserEmail.setText("");
        edtUserPassword.setText("");
    }
}
package com.eitgh.registers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eitgh.registers.entities.User;

public class FirstAccessActivity extends AppCompatActivity {

    private EditText edtUserName, edtUserEmail, edtUserPassword;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_access);

        edtUserName = findViewById(R.id.edtUsername);
        edtUserEmail = findViewById(R.id.edtemail);
        edtUserPassword = findViewById(R.id.edtPassword);
        btnSave = findViewById(R.id.btnNewUser);

        btnSave.setOnClickListener(v -> {
            User user = createUser();
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
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
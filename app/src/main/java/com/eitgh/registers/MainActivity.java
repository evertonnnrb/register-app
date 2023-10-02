package com.eitgh.registers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.eitgh.registers.entities.User;

public class MainActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        textViewWelcome = findViewById(R.id.txtWelcome);
        textViewWelcome.setText("Welcome "+user.getName());
    }
}
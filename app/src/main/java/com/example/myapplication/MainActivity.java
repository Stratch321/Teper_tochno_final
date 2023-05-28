package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reg = findViewById(R.id.regbutton);
        Button log = findViewById(R.id.loginbutton);
        EditText login = findViewById(R.id.loginbox);
        EditText pass = findViewById(R.id.passboxmain);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(MainActivity.this, regform.class);
                startActivity(reg);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mauth = FirebaseAuth.getInstance();
                mauth.signInWithEmailAndPassword(login.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Вход выполнен", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, main_app.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "вход не выполнен, чекать логи", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class regform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regform);
        Button reg = findViewById(R.id.regbutton);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText login = findViewById(R.id.emailbox);
                EditText pass = findViewById(R.id.passbox);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(login.getText().toString(), pass.getText().toString()).addOnCompleteListener(regform.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser usr = mAuth.getCurrentUser();
                            Log.d(TAG,"Succesful create user with id: " + usr.getUid());
                            Toast.makeText(regform.this, "Регистрация выполнена успешно", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Log.w(TAG, "Failed create user");
                            //TODO Стереть маты
                            Toast.makeText(regform.this, "Регистрация не выполнена, пошёл нахуй", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        Button back = findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(regform.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
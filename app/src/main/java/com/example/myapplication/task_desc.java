package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class task_desc extends AppCompatActivity {

    FirebaseFirestore store;
    FirebaseUser user;
    Button btn1;
    Button btn2;
    TextView  task_desc, task_cat, task_dep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_desc);
        store = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        btn1 = findViewById(R.id.Accept);
        btn2 = findViewById(R.id.revert);
        task_desc = findViewById(R.id.task_desc);
        task_cat = findViewById(R.id.task_cat);
        task_dep = findViewById(R.id.task_dep);
        String desc_card = getIntent().getStringExtra("namedesc");

        store.collection("tasks").whereEqualTo("User_id", user.getUid())
                .whereEqualTo("Description", desc_card).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult())
                    {
                        task_desc.setText(queryDocumentSnapshot.get("Description").toString());
                        task_cat.setText(queryDocumentSnapshot.get("Category").toString());
                        task_dep.setText(queryDocumentSnapshot.get("Department").toString());
                    }
                }
            }
        });



    }
}
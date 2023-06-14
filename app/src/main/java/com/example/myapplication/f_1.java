package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f_1.
     */
    // TODO: Rename and change types and number of parameters
    public static f_1 newInstance(String param1, String param2) {
        f_1 fragment = new f_1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView recyclerView;
    task_infoAdapter task_infoAdapter;
    FirebaseAuth auth;
    FirebaseFirestore mbase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_1, container, false);
        FloatingActionButton bt1 = view.findViewById(R.id.floating_button);
        mbase = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        FirestoreRecyclerOptions<task_info> options = new
                FirestoreRecyclerOptions.Builder<task_info>().setQuery(mbase.collection("tasks")
                .whereEqualTo("User_id", user.getUid())
                .whereNotEqualTo("Status", "Completed"), task_info.class).build();
        task_infoAdapter = new task_infoAdapter(options);
        recyclerView.setAdapter(task_infoAdapter);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), create_task.class);
                startActivity(intent);
            }
        });
        return view;

    }
    @Override public void onStart()
    {
        super.onStart();
        task_infoAdapter.startListening();

    }

    @Override public void onStop()
    {
        super.onStop();
        task_infoAdapter.stopListening();
    }
}
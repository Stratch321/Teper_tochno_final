package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link f_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public f_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f_2.
     */
    // TODO: Rename and change types and number of parameters
    public static f_2 newInstance(String param1, String param2) {
        f_2 fragment = new f_2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    task_infoAdapter adapter;
    FirebaseAuth auth;
    FirebaseFirestore store;
    FirebaseUser user;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f_2, container, false);
        recyclerView = view.findViewById(R.id.recycler2);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        store = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        FirestoreRecyclerOptions<task_info> options = new
                FirestoreRecyclerOptions.Builder<task_info>().setQuery(store.collection("tasks")
                .whereEqualTo("User_id", user.getUid())
                .whereEqualTo("Status", "Completed"), task_info.class).build();
        adapter = new task_infoAdapter(options);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}
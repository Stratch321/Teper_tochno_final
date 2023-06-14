package com.example.myapplication;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class task_infoAdapter extends FirestoreRecyclerAdapter<task_info, task_infoAdapter.task_infoViewHolder> {
    public task_infoAdapter(@NonNull FirestoreRecyclerOptions<task_info> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull task_infoViewHolder holder, int position, @NonNull task_info model) {
        holder.task_name.setText(model.getDescription());
        holder.task_category.setText(model.getCategory());
        holder.task_department.setText(model.getDepartment());
    }

    @NonNull
    @Override
    public task_infoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new task_infoAdapter.task_infoViewHolder(view);
    }

    class task_infoViewHolder extends RecyclerView.ViewHolder{
        TextView task_name, task_category, task_department;

        public task_infoViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_category = itemView.findViewById(R.id.task_category);
            task_department = itemView.findViewById(R.id.task_department);
        }
    }
}



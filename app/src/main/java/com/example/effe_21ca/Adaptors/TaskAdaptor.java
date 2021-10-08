package com.example.effe_21ca.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.models.TASKS;
import com.example.effe_21ca.models.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class TaskAdaptor extends FirebaseRecyclerAdapter<TASKS,TaskAdaptor.TaskViewholder>{




    public TaskAdaptor(@NonNull FirebaseRecyclerOptions<TASKS> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewholder holder, int position, @NonNull TASKS model) {

        holder.title.setText(model.getTitle());
        holder.link.setText(model.getLink());
        holder.points.setText(model.getPoint());

    }

    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tasks, parent, false);
       return new TaskViewholder(view);

    }



    class TaskViewholder extends RecyclerView.ViewHolder {

        TextView title,link,points;
        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.event_title);
            link=itemView.findViewById(R.id.event_description);
            points=itemView.findViewById(R.id.points);

        }
    }
}

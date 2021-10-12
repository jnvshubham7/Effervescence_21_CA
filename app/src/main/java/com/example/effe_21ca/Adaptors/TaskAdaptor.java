package com.example.effe_21ca.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.models.TASKS;
import com.example.effe_21ca.models.Users;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class TaskAdaptor extends FirebaseRecyclerAdapter<TASKS,TaskAdaptor.TaskViewholder>{

    private Activity context;
    // private Activity activity;







    public TaskAdaptor(@NonNull FirebaseRecyclerOptions<TASKS> options , Activity context) {
        super(options) ;
        this.context=context;

    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewholder holder, int position, @NonNull TASKS model) {

        holder.title.setText(model.getTitle());
        holder.link.setText(model.getLink());


        holder.points.setText(String.valueOf(model.getPoints()));

        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                context.startActivityForResult(intent,33);

            }
        });






    }



    @NonNull
    @Override
    public TaskViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tasks, parent, false);
        return new TaskViewholder(view);

    }



    class TaskViewholder extends RecyclerView.ViewHolder {

        TextView title,link,points;
        Button upload;
        public TaskViewholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.event_title);
            link=itemView.findViewById(R.id.event_description);
            upload=itemView.findViewById(R.id.upload_button);
            points=itemView.findViewById(R.id.events_points);




        }
    }
}
package com.example.effe_21ca.Adaptors;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.models.TASKS;

import java.util.ArrayList;

public class TaskAdaptor extends RecyclerView.Adapter<TaskAdaptor.ViewHolder>{

    private Activity context;
    private ArrayList<TASKS> arrayList;

    public TaskAdaptor(ArrayList<TASKS> arrayList , Activity context) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TASKS model = arrayList.get(position);

        holder.title.setText(model.getTitle());
        holder.link.setText(model.getLink());

        Log.d("id",model.getTaskId());


        holder.points.setText(String.valueOf(model.getPoints()));

        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra("id",model.getTaskId());
                intent.putExtra("position",holder.getAbsoluteAdapterPosition());
                intent.putExtra("score",model.getPoints());
                LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-event-name"));
                context.setIntent(intent);
                context.startActivityForResult(intent,33);
            }
        });

    }

    private void removeItem(int actualPosition) {
        arrayList.remove(actualPosition);
        notifyItemRemoved(actualPosition);
        notifyItemRangeChanged(actualPosition, arrayList.size());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_tasks, parent, false);
        Log.d("size", String.valueOf(arrayList.size()));
        return new ViewHolder(view);

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,link,points;
        Button upload;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.event_title);
            link=itemView.findViewById(R.id.event_description);
            upload=itemView.findViewById(R.id.upload_button);
            points=itemView.findViewById(R.id.events_points);
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            if(message.equals("delete")){
                LocalBroadcastManager.getInstance(context).unregisterReceiver(mMessageReceiver);
                removeItem(intent.getIntExtra("position",0));
            }
            Log.d("receiver", "Got message: " + message);
        }
    };

}
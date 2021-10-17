package com.example.effe_21ca.Adaptors;

import static java.text.DateFormat.getDateTimeInstance;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.models.TASKS;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskNotiAdaptor extends RecyclerView.Adapter<TaskNotiAdaptor.YasksNotiViewHolder> {

    private Activity context;
    private ArrayList<TASKS> arrayList;

    public TaskNotiAdaptor(Activity context, ArrayList<TASKS> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public YasksNotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notification, parent, false);
        Log.d("size", String.valueOf(arrayList.size()));
        return new YasksNotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YasksNotiViewHolder holder, int position) {

        TASKS model = arrayList.get(position);
        String Cdate=getTimeDate(model.getTimestamp());

        holder.title.setText(model.getTitle());
        holder.link.setText(model.getLink());
        holder.points.setText(String.valueOf(model.getPoints()));
        holder.date.setText(Cdate);
    }

    public static String getTimeDate(long timestamp){
        try{
            DateFormat dateFormat = getDateTimeInstance();
            Date netDate = (new Date(timestamp));
            return dateFormat.format(netDate);
        } catch(Exception e) {
            return "date";
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class YasksNotiViewHolder extends RecyclerView.ViewHolder {

        TextView title,link,points;
        TextView date;

        public YasksNotiViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.event_title1);
            link=itemView.findViewById(R.id.event_description1);

            points=itemView.findViewById(R.id.events_points1);
            date=itemView.findViewById(R.id.date);
        }
    }


}

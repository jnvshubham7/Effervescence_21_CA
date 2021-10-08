package com.example.effe_21ca.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.models.Users;

import java.util.ArrayList;

public class Users_Adapter extends RecyclerView.Adapter<Users_Adapter.UsersViewHolder> {

    Context context;
    ArrayList<Users> users;

    public Users_Adapter(Context context, ArrayList<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_leaderboard, parent, false);

        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        Users user = users.get(position);

        holder.userName.setText(user.getUserName());
       // holder.points.setText(user.getPoints());

//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, UserFragment.class);
//            intent.putExtra("name", user.getUserName());
//
//            intent.putExtra("uid", user.getUid());
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView userName,srNoTextView,points;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.name1);
            srNoTextView=itemView.findViewById(R.id.srNoTextView);
            points=itemView.findViewById(R.id.points);

            //  binding = RowConversationBinding.bind(itemView);
        }
    }

}
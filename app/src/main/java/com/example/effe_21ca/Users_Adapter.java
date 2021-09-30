//package com.example.effe_21ca;
//
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.chatsapp.Activities.Chat_Activity;
//import com.example.chatsapp.Models.User;
//import com.example.chatsapp.databinding.RowConversationBinding;
//import com.example.effe_21ca.models.Users;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class Users_Adapter extends RecyclerView.Adapter<Users_Adapter.UsersViewHolder> {
//
//    Context context;
//    ArrayList<Users> users;
//
//    public Users_Adapter(Context context, ArrayList<Users> users) {
//        this.context = context;
//        this.users = users;
//    }
//
//    @NonNull
//    @Override
//    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.list_item_leaderboard, parent, false);
//
//        return new UsersViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
//        Users user = users.get(position);
//
//
//
//
//
//
//        holder.binding.name1.setText(user.getUserName());
//
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, Chat_Activity.class);
//            intent.putExtra("name", user.getName());
//            intent.putExtra("image", user.getProfileImage());
//            intent.putExtra("uid", user.getUid());
//            context.startActivity(intent);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return users.size();
//    }
//
//    public static class UsersViewHolder extends RecyclerView.ViewHolder {
//
//        RowConversationBinding binding;
//
//        public UsersViewHolder(@NonNull View itemView) {
//            super(itemView);
//            binding = RowConversationBinding.bind(itemView);
//        }
//    }
//
//}
//

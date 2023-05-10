package com.example.ettaki.chat.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ettaki.R;
import com.example.ettaki.chat.MessageActivity;
import com.example.ettaki.chat.models.UsersModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersListAdapter extends FirebaseRecyclerAdapter<UsersModel, UsersListAdapter.UsersViewHolder> {

    public UsersListAdapter(@NonNull FirebaseRecyclerOptions<UsersModel> options) {
        super(options);

    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_users_chat_list, parent, false);
        return new UsersViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull UsersModel model) {
       String currentUser= FirebaseAuth.getInstance().getCurrentUser().getUid();
       if (!currentUser.equals(model.getUserId())){
           String fullName = model.getFirstName() + " " + model.getLastName();
           holder.mUserName.setText(fullName);
           holder.mUserType.setText(model.getType());
           Glide.with(holder.mProfileImage.getContext())
                   .load(model.getImage())
                   .centerCrop()
                   .placeholder(R.drawable.loading)
                   .into(holder.mProfileImage);
           holder.itemView.setOnClickListener(view -> {
               Intent msgActivity = new Intent(holder.mUserName.getContext(), MessageActivity.class);
               msgActivity.putExtra("name", fullName);
               msgActivity.putExtra("sender_id", model.getUserId());
               holder.itemView.getContext().startActivity(msgActivity);
           });
       }
       else {
           holder.itemView.setVisibility(View.GONE);
           Toast.makeText(holder.mUserName.getContext(),"لايوجد مستخدمين من النوع "+model.getType(),Toast.LENGTH_LONG).show();
       }

    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        public TextView mUserName, mUserType;
        public CircleImageView mProfileImage;
        public ImageView onlineImage;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            mUserName = itemView.findViewById(R.id.user_name);
            mUserType = itemView.findViewById(R.id.user_category);
            mProfileImage = itemView.findViewById(R.id.user_image);
            onlineImage = itemView.findViewById(R.id.online_image);
            onlineImage.setImageResource(R.drawable.ic_baseline_chat_bubble_24);

        }
    }


}
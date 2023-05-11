package com.example.ettaki.chat.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.ettaki.R;
import com.example.ettaki.chat.Helper;
import com.example.ettaki.chat.MessageActivity;
import com.example.ettaki.chat.models.MessageModel;
import com.example.ettaki.chat.models.UsersModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */

public class CurrentUserMessageListAdapter extends RecyclerView.Adapter<CurrentUserMessageListAdapter.CurrentUserMessageListViewHolder> {

    private final List<MessageModel> mMessageList;
    private FirebaseAuth mAuth;
    private final Context context;

    public CurrentUserMessageListAdapter(Context context, List<MessageModel> mMessageList) {
        this.context = context;
        this.mMessageList = mMessageList;
    }


    @NonNull
    @Override
    public CurrentUserMessageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_user_home_message_list, parent, false);
        return new CurrentUserMessageListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CurrentUserMessageListViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();

        final MessageModel msg = mMessageList.get(position);
        final DatabaseReference senderInfo = FirebaseDatabase.getInstance().getReference().child("User");
        final String dId = mAuth.getCurrentUser().getUid();

        if (!dId.equals(msg.getFrom())) {
            senderInfo.child(msg.getFrom()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UsersModel user = dataSnapshot.getValue(UsersModel.class);
                    if (user != null) {
                        String fullName = user.getFirstName() + " " + user.getLastName();
                        holder.mMessageSender.setText(Helper.subStringH(fullName, 14));
                        holder.mMessageText.setText(Helper.subStringH(msg.getMsg(), 19));
                        holder.mTime.setText(Helper.getTimeAgo(msg.getTime(), context));

                        holder.itemView.setOnClickListener(view -> {
                            Log.d("TAG", "onClick: " + fullName);

                            Intent i = new Intent(context, MessageActivity.class);
                            i.putExtra("sender_id", msg.getFrom());
                            i.putExtra("name", fullName);
                            i.putExtra("chatImage", user.getImage());
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                            mMessageList.clear();
                        });
                        Glide.with(context)
                                .load(user.getImage())
                                .centerCrop()
                                .placeholder(R.drawable.ic_round_cloud_download_24)
                                .into(holder.ProfileImage);
                    } else {
                        holder.mMessageSender.setText("Not Found");
                        holder.mMessageText.setText("");
                        holder.mTime.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            senderInfo.child(msg.getTo()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UsersModel pp = dataSnapshot.getValue(UsersModel.class);

                    if (pp != null) {
                        String fullName = pp.getFirstName() + " " + pp.getLastName();
                        holder.mMessageSender.setText(Helper.subStringH(fullName, 14));
                        holder.mMessageText.setText(Helper.subStringH(msg.getMsg(), 19));
                        holder.mTime.setText(Helper.getTimeAgo(msg.getTime(), context));
                        Glide.with(context)
                                .load(pp.getImage())
                                .centerCrop()
                                .placeholder(R.drawable.ic_round_cloud_download_24)
                                .into(holder.ProfileImage);

                        holder.itemView.setOnClickListener(view -> {

                            Intent i = new Intent(context, MessageActivity.class);
                            i.putExtra("sender_id", msg.getTo());
                            i.putExtra("name", fullName);
                            i.putExtra("chatImage", pp.getImage());
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                            mMessageList.clear();

                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public static class CurrentUserMessageListViewHolder extends RecyclerView.ViewHolder {

        CircleImageView ProfileImage;
        TextView mMessageSender, mMessageText, mTime;

        public CurrentUserMessageListViewHolder(View itemView) {
            super(itemView);

            ProfileImage = itemView.findViewById(R.id.msg_list_profile_img);
            mMessageSender = itemView.findViewById(R.id.message_sender);
            mMessageText = itemView.findViewById(R.id.msg_from_patient);
            mTime = itemView.findViewById(R.id.msg_send_time);
        }
    }
}

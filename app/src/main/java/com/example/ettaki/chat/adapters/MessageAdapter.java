package com.example.ettaki.chat.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ettaki.R;
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


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private final List<MessageModel> mMessageList;
    private FirebaseAuth mAuth;
    private final Context context;

    public MessageAdapter(Context context, List<MessageModel> mMessageList) {
        this.context = context;
        this.mMessageList = mMessageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_single_layout, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        mAuth = FirebaseAuth.getInstance();
        String current_user = mAuth.getCurrentUser().getUid();

        MessageModel msg = mMessageList.get(position);

        String from_user = msg.getFrom();
        // get firebase user reference from realtime database
        final DatabaseReference senderInfo = FirebaseDatabase.getInstance().getReference().child("User");
        senderInfo.child(from_user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UsersModel user = dataSnapshot.getValue(UsersModel.class);
                if (user != null) {
                    Glide.with(context)
                            .load(user.getImage())
                            .centerCrop()
                            .placeholder(R.drawable.ic_round_cloud_download_24)
                            .into(holder.mProfileImage);
                }
//                notifyItemChanged(position);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        if (from_user.equals(current_user)) {
            holder.mTextMessage.setBackgroundResource(R.drawable.background_gradient_alt);
            holder.mTextMessage.setTextColor(Color.BLACK);
            Glide.with(context)
                    .load(mAuth.getCurrentUser().getPhotoUrl())
                    .centerCrop()
                    .placeholder(R.drawable.ic_round_person_24)
                    .into(holder.mProfileImage);
        } else {
            holder.mTextMessage.setBackgroundResource(R.drawable.background_gradient);
            holder.mTextMessage.setTextColor(Color.BLACK);
        }

        String msg_type = msg.getType();

        if (msg_type.equals("text")) {
            holder.mTextMessage.setText(msg.getMsg());
            holder.mSendImage.setVisibility(View.GONE);
        } else {
            holder.mTextMessage.setVisibility(View.GONE);
            Glide.with(context)
                    .load(msg.getMsg())
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .into(holder.mSendImage);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextMessage;
        public CircleImageView mProfileImage;
        public ImageView mSendImage;

        public MessageViewHolder(View itemView) {
            super(itemView);

            mTextMessage = itemView.findViewById(R.id.txt_msg);
            mProfileImage = itemView.findViewById(R.id.message_sender_profile_image);
            mSendImage = itemView.findViewById(R.id.msg_image);
        }
    }


}

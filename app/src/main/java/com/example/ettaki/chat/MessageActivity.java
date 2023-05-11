package com.example.ettaki.chat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ettaki.R;
import com.example.ettaki.chat.adapters.MessageAdapter;
import com.example.ettaki.chat.models.MessageModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    public static final String TAG = "MessageActivity";
    //RecyclerView
    RecyclerView mMessageView;
    List<MessageModel> mMessageList = new ArrayList<>();
    public LinearLayoutManager mLinearLayout;
    public MessageAdapter mAdapter;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // database
    private DatabaseReference mUserRef, mDatabase;

    public String currentUserID;
    public String senderId;

    private ImageView sendMessageButton;
    private EditText messageTextBox;

    //toolbar content
    CircleImageView imgProfileChat;
    TextView txtChatName, txtOnline, txtType;

    //For loading message
    private int itemPos = 0;
    private String mLastKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent i = getIntent();
        senderId = i.getStringExtra("sender_id");
        String fullName = i.getStringExtra("name");
        String chatImage = i.getStringExtra("chatImage");


        //Widget initialization
        //Widget
        sendMessageButton = findViewById(R.id.message_send_button);
        messageTextBox = findViewById(R.id.send_text_message);
        //toolbar content
        txtChatName = findViewById(R.id.txtChatName);
        imgProfileChat = findViewById(R.id.imgProfileChat);
        txtType = findViewById(R.id.txtType);
        txtOnline = findViewById(R.id.txtOnline);
        txtChatName.setText(fullName);
        Glide.with(imgProfileChat.getContext())
                .load(chatImage)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imgProfileChat);

        //RecyclerView
        mAdapter = new MessageAdapter(this, mMessageList);
        mMessageView = findViewById(R.id.message_list);
        mLinearLayout = new LinearLayoutManager(this);
        mMessageView.setHasFixedSize(true);
        mMessageView.setLayoutManager(mLinearLayout);
        mMessageView.getRecycledViewPool().setMaxRecycledViews(0, 0);
        loadMessage();
        mMessageView.setAdapter(mAdapter);
        //Firebase init
        //Firebase Auth init
        mAuth = FirebaseAuth.getInstance();

        //Firebase init
        //Firebase Auth init
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("User").child(currentUserID);

        DatabaseReference userInfo = FirebaseDatabase.getInstance().getReference().child("User");
        userInfo.child(senderId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String online = dataSnapshot.child("online").getValue().toString();
                if (online.equals("true")) {
                    txtOnline.setText("متصل الان");
                } else {
                    txtOnline.setText("غير متصل");
                }
                txtType.setText(dataSnapshot.child("type").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //For checking isOnline
                mUserRef.child("online").onDisconnect().setValue(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "signed_in:" + user.getUid());

            } else {
                // User is signed out
                Log.d(TAG, "signed_out");
            }
        };

        //Chatting Activity
        mDatabase.child("Chat").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (!dataSnapshot.hasChild(senderId)) {
                    Map<String, Object> chatAddMap = new HashMap<>();
                    chatAddMap.put("seen", false);
                    chatAddMap.put("timestamp", ServerValue.TIMESTAMP);

                    Map<String, Object> chatUserMap = new HashMap<>();
                    chatUserMap.put("Chat/" + currentUserID + "/" + senderId, chatAddMap);
                    chatUserMap.put("Chat/" + senderId + "/" + currentUserID, chatAddMap);


                    mDatabase.updateChildren(chatUserMap, (databaseError, databaseReference) -> {

                        if (databaseError != null) {
                            Log.d(TAG, "Message sending failed for, database failure.");
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Onclick for send button
        sendMessageButton.setOnClickListener(view -> sendMessage());

    }

    //For sending message patient to doctor
    public void sendMessage() {

        String message = messageTextBox.getText().toString();

        if (!TextUtils.isEmpty(message)) {

            String current_user_ref = "Message/" + currentUserID + "/" + senderId;
            String doctor_ref = "Message/" + senderId + "/" + currentUserID;

            DatabaseReference user_message_push = mDatabase.child("Message")
                    .child(currentUserID).child(senderId).push();

            String push_id = user_message_push.getKey();

            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("msg", message);
            messageMap.put("seen", false);
            messageMap.put("type", "text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("from", currentUserID);
            messageMap.put("to", senderId);

            Map<String, Object> messageUserMap = new HashMap<>();
            messageUserMap.put(doctor_ref + "/" + push_id, messageMap);
            messageUserMap.put(current_user_ref + "/" + push_id, messageMap);


            messageTextBox.setText("");

            mDatabase.updateChildren(messageUserMap, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.d(TAG, "Message sending failed for, database failure.");
                }
            });
        }
    }

    //For getting message from database
    public void loadMessage() {
        mAuth = FirebaseAuth.getInstance();
        String dId = mAuth.getCurrentUser().getUid();

        DatabaseReference retriveMessae = FirebaseDatabase.getInstance().getReference().child("Message").child(dId).child(senderId);
        Query msgQuery = retriveMessae;

        msgQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                MessageModel message = dataSnapshot.getValue(MessageModel.class);

                itemPos++;
                if (itemPos == 1) {
                    mLastKey = dataSnapshot.getKey();
                }

                mMessageList.add(message);
                mAdapter.notifyDataSetChanged();
                //For showing the last message in view
                mMessageView.scrollToPosition(mMessageList.size() - 1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            mUserRef.child("online").setValue(true);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        mUserRef.child("online").setValue(false);
    }
}
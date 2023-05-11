package com.example.ettaki.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ettaki.R;
import com.example.ettaki.chat.adapters.CurrentUserMessageListAdapter;
import com.example.ettaki.chat.models.MessageModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentUserChatsActivity extends AppCompatActivity {

    static final String TAG = "DoctorHome";

    //RecyclerView
    RecyclerView mMessageView;
    ArrayList<MessageModel> mMessageList = new ArrayList<>();
    public LinearLayoutManager mLinearLayout;
    public CurrentUserMessageListAdapter mAdapter;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    // database
    private DatabaseReference mUserRef, mDatabase;
    private Query query;
    public String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_user_chats);

        //Firebase Auth init
        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(getApplication(), "كل المحادثات", Toast.LENGTH_SHORT).show();

        //Firebase Auth init
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("User").child(currentUserId);

        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // For checking is Online
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


        //RecyclerView

        mAdapter = new CurrentUserMessageListAdapter(this, mMessageList);
        mMessageView = findViewById(R.id.Current_user_message_list_recycler);
        mLinearLayout = new LinearLayoutManager(this);
        mMessageView.setHasFixedSize(true);
        mMessageView.setLayoutManager(mLinearLayout);
        msgInfo();
        mMessageView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void msgInfo() {
        mAuth = FirebaseAuth.getInstance();
        final String uid = mAuth.getCurrentUser().getUid();

        final DatabaseReference retriveId = FirebaseDatabase.getInstance().getReference().child("Chat");
        retriveId.child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                Log.d("key:", dataSnapshot.getKey());
                String ss = String.valueOf(dataSnapshot.getKey());
                final String dId = mAuth.getCurrentUser().getUid();

                DatabaseReference retriveMsg = FirebaseDatabase.getInstance().getReference()
                        .child("Message").child(dId).child(ss);
                query = retriveMsg.limitToLast(1);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                        MessageModel message = dataSnapshot.getValue(MessageModel.class);
                        mMessageList.add(message);
                        Log.d("message:", message.getMsg() + " " + message.getFrom());
                        mAdapter.notifyDataSetChanged();
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
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                mAdapter.notifyDataSetChanged();
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
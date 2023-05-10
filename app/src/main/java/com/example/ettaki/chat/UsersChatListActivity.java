package com.example.ettaki.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ettaki.R;
import com.example.ettaki.chat.adapters.UsersListAdapter;
import com.example.ettaki.chat.models.UsersModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class UsersChatListActivity extends AppCompatActivity {

    public String type;

    //RecyclerView
    private RecyclerView mUsersChatListRCV;
    UsersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_chat_list);

        try {
            Intent i = getIntent();
            type = i.getStringExtra("type");

            mUsersChatListRCV = findViewById(R.id.user_list_recycler);
            GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);//انشاء قئمة list لعرض البيانات
            mUsersChatListRCV.setLayoutManager(layoutManager);
            FirebaseRecyclerOptions<UsersModel> options =
                    new FirebaseRecyclerOptions.Builder<UsersModel>().setQuery(FirebaseDatabase.getInstance().getReference().child("User").orderByChild("type").equalTo(type), UsersModel.class).build();//استرجاع البيانات من قاعدة البيانات
            adapter = new UsersListAdapter(options);
            mUsersChatListRCV.setAdapter(adapter);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
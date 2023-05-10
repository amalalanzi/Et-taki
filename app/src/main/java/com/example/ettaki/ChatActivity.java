package com.example.ettaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ettaki.chat.UsersChatListActivity;

public class ChatActivity extends AppCompatActivity {

    Button btnDoctorsChat,btnSupportChat,btnPatientChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        btnDoctorsChat=findViewById(R.id.btnDoctorsChat);
        btnPatientChat=findViewById(R.id.btnPatientChat);
        btnSupportChat=findViewById(R.id.btnSupportChat);

        //String[] item = {" مريض ", " دكتور ", " داعم "};
        btnDoctorsChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent chat=new Intent(this,)
                Intent listActivity = new Intent(getApplicationContext(), UsersChatListActivity.class);
                listActivity.putExtra("type", " دكتور ");
                startActivity(listActivity);
            }
        });
        btnPatientChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent chat=new Intent(this,)
                Intent listActivity = new Intent(getApplicationContext(), UsersChatListActivity.class);
                listActivity.putExtra("type", " مريض ");
                startActivity(listActivity);
            }
        });
        // داعم
        btnSupportChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent chat=new Intent(this,)
                Intent listActivity = new Intent(getApplicationContext(), UsersChatListActivity.class);
                listActivity.putExtra("type", " داعم ");
                startActivity(listActivity);
            }
        });

    }
}
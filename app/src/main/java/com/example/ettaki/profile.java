package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class profile extends AppCompatActivity {

    private TextView UserName , UserEmail , UserType , logout , Name;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!SharedPrefManager.getInstance(this).isLoggedin()){
            finish();
            startActivity(new Intent(this , ettaki.class));
        }

        UserName = (TextView) findViewById(R.id.fullName);
        UserEmail =(TextView) findViewById(R.id.userEmail);
        UserType =(TextView) findViewById(R.id.UserType);
        logout = (TextView) findViewById(R.id.logout);
        Name = (TextView) findViewById(R.id.name);
        back = (ImageView) findViewById(R.id.back);


        UserName.setText(SharedPrefManager.getInstance(this).getFullName());
        UserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        UserType.setText(SharedPrefManager.getInstance(this).getUserType());
        Name.setText(SharedPrefManager.getInstance(this).getFullName());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                finish();
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });



      //  menu();
    }

//
//    public void menu(){
//
//        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
//        NavigationView navigationView = findViewById(R.id.navigationView);
//        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//
//        navigationView = findViewById(R.id.navigationView);
//        navigationView.setItemIconTintList(null);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menuProfile: {
//                        Intent intent = new Intent(getApplicationContext(), profile.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuHome: {
//                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuUs: {
//                        Intent intent = new Intent(getApplicationContext(), aboutUs.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuPositive: {
//                        Intent intent = new Intent(getApplicationContext(), positive.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuChat: {
//                        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuReminder: {
//                        Intent intent = new Intent(getApplicationContext(), reminders.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuُTest: {
//                        Intent intent = new Intent(getApplicationContext(), startTest.class);
//                        startActivity(intent);
//                        break;
//                    }
//
//                    case R.id.menuExersice: {
//                        Intent intent = new Intent(getApplicationContext(), doExercises.class);
//                        startActivity(intent);
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//    }


}
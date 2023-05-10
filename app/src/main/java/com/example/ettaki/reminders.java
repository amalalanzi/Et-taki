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
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class reminders extends AppCompatActivity {


    DrawerLayout drawerLayout ;
    LinearLayout Bar , exer;
    NavigationView navigationView ;
    ImageView image ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        Bar = findViewById(R.id.Bar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        image = (ImageView) findViewById(R.id.imageMenu);


        menu();


    }












    public void menu(){


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
                exer.setVisibility(View.GONE);


            }
        });

        Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exer.setVisibility(View.VISIBLE);




            }
        });


        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuProfile: {
                        Intent intent = new Intent(getApplicationContext(), profile.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuHome: {
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuUs: {
                        Intent intent = new Intent(getApplicationContext(), aboutUs.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuPositive: {
                        Intent intent = new Intent(getApplicationContext(), positive.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuChat: {
                        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuReminder: {
                        Intent intent = new Intent(getApplicationContext(), reminders.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuُTest: {
                        Intent intent = new Intent(getApplicationContext(), startTest.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.menuExersice: {
                        Intent intent = new Intent(getApplicationContext(), doExercises.class);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }
        });


    }
}
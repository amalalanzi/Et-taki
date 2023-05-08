package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.navigation.NavigationView;

public class positive extends AppCompatActivity {



    ImageView v1 , v2, v3, v4 , v5, v6;
    VideoView video1 , video2 , video3, video4 , video5,video6 ;
    Button exit;


    ConstraintLayout ved ;

    DrawerLayout drawerLayout ;
    LinearLayout Bar , pos;
    NavigationView navigationView ;
    ImageView image ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positive);

        ved = findViewById(R.id.vedio);
        pos = findViewById(R.id.menusPositive);
        exit = findViewById(R.id.exit);


        Bar = findViewById(R.id.Bar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        image = (ImageView) findViewById(R.id.imageMenu);



        v1 = (ImageView) findViewById(R.id.imagev1);
        v2 = (ImageView) findViewById(R.id.imagev2);
        v3 = (ImageView) findViewById(R.id.imagev3);
        v4 = (ImageView) findViewById(R.id.imagev4);
        v5 = (ImageView) findViewById(R.id.imagev5);
        v6 = (ImageView) findViewById(R.id.imagev6);


        video1 = (VideoView) findViewById(R.id.imagevedio1);
        video2 = (VideoView) findViewById(R.id.imagevedio2);
        video3 = (VideoView) findViewById(R.id.imagevedio3);
        video4 = (VideoView) findViewById(R.id.imagevedio4);
        video5 = (VideoView) findViewById(R.id.imagevedio5);
        video6 = (VideoView) findViewById(R.id.imagevedio6);


        ved.setVisibility(View.GONE);
        exit.setVisibility(View.GONE);




        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                pos.setVisibility(View.GONE);
                video1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med1 = new MediaController(positive.this);
                video1.setMediaController(med1);
                med1.setAnchorView(video1);
                video1.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });




        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                pos.setVisibility(View.GONE);
                video2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med1 = new MediaController(positive.this);
                video2.setMediaController(med1);
                med1.setAnchorView(video2);
                video2.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });



        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                pos.setVisibility(View.GONE);
                video3.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med1 = new MediaController(positive.this);
                video3.setMediaController(med1);
                med1.setAnchorView(video3);
                video3.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });


        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                pos.setVisibility(View.GONE);
                video4.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med1 = new MediaController(positive.this);
                video4.setMediaController(med1);
                med1.setAnchorView(video4);
                video4.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });



        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                pos.setVisibility(View.GONE);
                video5.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med1 = new MediaController(positive.this);
                video5.setMediaController(med1);
                med1.setAnchorView(video5);
                video5.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });



        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                pos.setVisibility(View.GONE);
                video6.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med1 = new MediaController(positive.this);
                video6.setMediaController(med1);
                med1.setAnchorView(video6);
                video6.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.GONE);
                exit.setVisibility(View.GONE);
                pos.setVisibility(View.VISIBLE);
                Intent i = new Intent(getApplicationContext(),positive.class);
                startActivity(i);

            }
        });



        menu();

    }




    public void menu(){


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);
                pos.setVisibility(View.GONE);


            }
        });

        Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos.setVisibility(View.VISIBLE);




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
                        Intent intent = new Intent(getApplicationContext(), chat.class);
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
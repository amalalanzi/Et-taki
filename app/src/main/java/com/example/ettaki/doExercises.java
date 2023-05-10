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

public class doExercises extends AppCompatActivity {

   ImageView ex1 , ex2, ex3, ex4;
    VideoView upperBody , coreStability , foomRoller, lowerBody;
    Button exit;


    ConstraintLayout ved ;


    DrawerLayout drawerLayout ;
    LinearLayout Bar , exer;
    NavigationView navigationView ;
    ImageView image ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exercises);

        ved = findViewById(R.id.vedio);
        exer = findViewById(R.id.menusExersice);
        exit = findViewById(R.id.exit);


        Bar = findViewById(R.id.Bar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        image = (ImageView) findViewById(R.id.imageMenu);



        ex1 = (ImageView) findViewById(R.id.UpperBody);
        ex2 = (ImageView) findViewById(R.id.CoreStability);
        ex3 = (ImageView) findViewById(R.id.FoamRollerMassage);
        ex4 = (ImageView) findViewById(R.id.LowerBody);


        upperBody = (VideoView) findViewById(R.id.UpperBodyvedio);
        coreStability = (VideoView) findViewById(R.id.CoreStabilityvedio);
        foomRoller = (VideoView) findViewById(R.id.FoamRollervedio);
        lowerBody = (VideoView) findViewById(R.id.LowerBodyvedio);




        ved.setVisibility(View.GONE);
        exit.setVisibility(View.GONE);


        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                exer.setVisibility(View.GONE);
                upperBody.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.upperbody1));
                MediaController med1 = new MediaController(doExercises.this);
                upperBody.setMediaController(med1);
                med1.setAnchorView(upperBody);
                upperBody.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);
            }
        });




        ex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                exer.setVisibility(View.GONE);
                coreStability.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.corestability));
                MediaController med2 = new MediaController(doExercises.this);
                coreStability.setMediaController(med2);
                med2.setAnchorView(coreStability);
                coreStability.start();


//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);


            }
        });

        ex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                exer.setVisibility(View.GONE);
                foomRoller.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.foamroller));
                MediaController med3 = new MediaController(doExercises.this);
                foomRoller.setMediaController(med3);
                med3.setAnchorView(foomRoller);
                foomRoller.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);


            }
        });

        ex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                exer.setVisibility(View.GONE);
                lowerBody.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.lowerbody));
                MediaController med4 = new MediaController(doExercises.this);
                lowerBody.setMediaController(med4);
                med4.setAnchorView(lowerBody);
                lowerBody.start();
//                Intent i = new Intent(getApplicationContext(),vedio_exersic.class);
//                startActivity(i);


            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ved.setVisibility(View.GONE);
                exit.setVisibility(View.GONE);
                exer.setVisibility(View.VISIBLE);
                Intent i = new Intent(getApplicationContext(),doExercises.class);
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
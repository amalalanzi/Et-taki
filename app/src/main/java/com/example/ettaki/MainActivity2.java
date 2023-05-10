package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {


    ImageView bgapp;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;


    TextView us , chat,  doexer , reminders, test , positive;
    DrawerLayout drawerLayout ;
    ActionBarDrawerToggle drawerToggle;
    LinearLayout Bar;
    NavigationView navigationView ;
    ImageView image ;



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Bar = findViewById(R.id.Bar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        image = (ImageView) findViewById(R.id.imageMenu);

        us =(TextView) findViewById(R.id.us);
        chat =(TextView) findViewById(R.id.do_chat);
        doexer =(TextView) findViewById(R.id.do_exersice);
        reminders =(TextView) findViewById(R.id.do_reminders);
        test =(TextView) findViewById(R.id.do_test);
        positive =(TextView) findViewById(R.id.be_positive);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        bgapp = (ImageView) findViewById(R.id.bgapp);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        menus = (LinearLayout) findViewById(R.id.menus);


        bgapp.animate().translationY(-1200).setDuration(1500).setStartDelay(1000);
        textsplash.animate().translationY(1000).alpha(0).setDuration(1550).setStartDelay(1000);

        texthome.startAnimation(frombottom);
        menus.startAnimation(frombottom);


        texthome.setVisibility(View.VISIBLE);
        menus.setVisibility(View.VISIBLE);




        menu();

        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, aboutUs.class);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        doexer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, doExercises.class);
                startActivity(intent);
            }
        });
        reminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, reminders.class);
                startActivity(intent);
            }
        });


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, startTest.class);
                startActivity(intent);
            }
        });

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, positive.class);
                startActivity(intent);
            }
        });



    }



    public void menu(){


        image.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                       drawerLayout.openDrawer(GravityCompat.START);
                       texthome.setVisibility(View.GONE);
                       menus.setVisibility(View.GONE);

               }
           });

        Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texthome.startAnimation(frombottom);
                menus.startAnimation(frombottom);


                texthome.setVisibility(View.VISIBLE);
                menus.setVisibility(View.VISIBLE);

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


//
//    @Override
//    public void onBackPressed() {



//        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
////            drawerLayout.closeDrawer(GravityCompat.START);
//
//            texthome.setVisibility(View.GONE);
//            menus.setVisibility(View.GONE);
//        }
//        else {
//
//            texthome.setVisibility(View.VISIBLE);
//            menus.setVisibility(View.VISIBLE);
//
//        }


//
//    }
}






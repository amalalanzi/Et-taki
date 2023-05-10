package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Test extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    String score;
    int a1 , a2, a3, a4, a5, a6, a7, a8, a9 ;
    int totalQuestion;
    int currentQuestionIndex ;
    String selectedAnswer ;
    Button clickedButton ;

    DrawerLayout drawerLayout ;
    RelativeLayout relativeLayout ;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView ;
    ImageView image ;
    LinearLayout Bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        image = (ImageView) findViewById(R.id.imageMenu);
        Bar = findViewById(R.id.Bar);



        totalQuestion = Questions.question.length;
        currentQuestionIndex = 0;
        selectedAnswer = "";
        score=" ";
        a1=0 ; a2=0; a3=0; a4=0; a5=0; a6=0; a7=0; a8=0; a9 =0;

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalQuestionsTextView.setText("مجموع الاسئلة: "+totalQuestion);

      loadNewQuestion();
      menu();

//
//      ansE.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              ansE.setBackgroundColor(Color.MAGENTA);
//              selectedAnswer = ansE.getText().toString();
//              Toast.makeText(getApplicationContext(), "yyee", Toast.LENGTH_LONG).show();
//
//
//
//          }
//      });
//
//
//        ansA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ansA.setBackgroundColor(Color.MAGENTA);
//                selectedAnswer = ansA.getText().toString();
//                Toast.makeText(getApplicationContext(), "yyee", Toast.LENGTH_LONG).show();
//
//
//
//            }
//        });
//
//      submitBtn.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              currentQuestionIndex++;
//            loadNewQuestion();
//
//          }
//      });


    }





    @Override
    public void onClick(View v) {


        clickedButton = (Button) v;


        if (clickedButton.getId() == R.id.submit_btn) {

            switch (currentQuestionIndex) {
                case 0: {

                    if (selectedAnswer.equals("نعم")) {
                        a1++; a2++; a3++; a4++; a5++; a6++; a7++; a8++; a9++;

                    }else if (selectedAnswer.equals("لا")) {

                    }

                }case 1: {

                    if (selectedAnswer.equals("نعم")) {
                        a2++; a3++; a4++; a5++; a6++; a7++; a8++; a9++;


                    }else if (selectedAnswer.equals("لا")) {
                        a1++;
                    }

                }case 2: {

                    if (selectedAnswer.equals("نعم")) {
                        a1++; a2++; a3++; a4++; a5++;


                    }else if (selectedAnswer.equals("لا")) {
                       a6++; a7++; a8++; a9++;


                    }

                }case 3: {

                    if (selectedAnswer.equals("نعم")) {
                        a6++; a7++; a8++; a9++;


                    }else if (selectedAnswer.equals("لا")) {
                        a1++; a2++; a3++; a4++; a5++;

                    }
                }

                case 4: {
                    if ( selectedAnswer.equals("إعاقة خفيفة")) {
                        a2++;
                    }else if(selectedAnswer.equals("إعاقة متوسطة")){
                        a3++;
                    }else if(selectedAnswer.equals("إعاقة شديدة")){
                        a4++; a5++; a6++; a7++; a8++; a9++;
                    }else if(selectedAnswer.equals("ليس لدي أي منها")){
                        a1++;
                    }
                }

                case 5: {
                    if ( selectedAnswer.equals("من 360 فأكثر")) {
                        a1++; a2++; a3++; a4++;
                    }else if(selectedAnswer.equals("من 100 إلى 200 م")){
                       a5++;
                    }else if(selectedAnswer.equals("من 0 إلى 100 م")){
                        a6++; a7++; a8++; a9++;
                    }


                } case 7: {
                    if ( selectedAnswer.equals("في كرسي متحرك")) {
                        a7++;
                    }else if(selectedAnswer.equals("محصورة في السرير")){
                        a8++;
                    }else if(selectedAnswer.equals("بين الكرسي والسرير")){
                       a9++;
                    }else if (selectedAnswer.equals("لا شي مما ذكر")){
                        a1++; a2++; a3++; a4++; a5++; a6++;
                    }
                }

            }

//            if(selectedAnswer.equals("نعم")){
//                score = "From 1 to 2";
//            }

                ansA.setBackgroundColor(Color.WHITE);
                ansB.setBackgroundColor(Color.WHITE);
                ansC.setBackgroundColor(Color.WHITE);
                ansD.setBackgroundColor(Color.WHITE);
                currentQuestionIndex++;
                loadNewQuestion();


            }else if (clickedButton.getId() == R.id.ans_A) {
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.GRAY);
                ansB.setBackgroundColor(Color.WHITE);
                ansC.setBackgroundColor(Color.WHITE);
                ansD.setBackgroundColor(Color.WHITE);

            } else if (clickedButton.getId() == R.id.ans_B) {
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.WHITE);
                ansC.setBackgroundColor(Color.WHITE);
                ansD.setBackgroundColor(Color.WHITE);

            } else if (clickedButton.getId() == R.id.ans_C) {
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.WHITE);
                ansB.setBackgroundColor(Color.WHITE);
                ansD.setBackgroundColor(Color.WHITE);

            } else if (clickedButton.getId() == R.id.ans_D) {
                selectedAnswer = clickedButton.getText().toString();
                clickedButton.setBackgroundColor(Color.GRAY);
                ansA.setBackgroundColor(Color.WHITE);
                ansB.setBackgroundColor(Color.WHITE);
                ansC.setBackgroundColor(Color.WHITE);
            }

    }


        private void loadNewQuestion () {

            if (currentQuestionIndex == totalQuestion) {
                finishQuiz();
                return;
            }

            switch (currentQuestionIndex) {

                case 0:

                case 1:

                case 2:

                case 3: {
                    questionTextView.setText(Questions.question[currentQuestionIndex]);
                    ansA.setText(Questions.choices[currentQuestionIndex][0]);
                    ansB.setText(Questions.choices[currentQuestionIndex][1]);
                    ansC.setVisibility(View.GONE);
                    ansD.setVisibility(View.GONE);
                    break;
                }

                case 4:
                case 6: {
                    questionTextView.setText(Questions.question[currentQuestionIndex]);
                    ansA.setText(Questions.choices[currentQuestionIndex][0]);
                    ansB.setText(Questions.choices[currentQuestionIndex][1]);
                    ansC.setText(Questions.choices[currentQuestionIndex][2]);
                    ansD.setText(Questions.choices[currentQuestionIndex][3]);
                    ansC.setVisibility(View.VISIBLE);
                    ansD.setVisibility(View.VISIBLE);
                    break;
                }

                case 5: {
                    questionTextView.setText(Questions.question[currentQuestionIndex]);
                    ansA.setText(Questions.choices[currentQuestionIndex][0]);
                    ansB.setText(Questions.choices[currentQuestionIndex][1]);
                    ansC.setText(Questions.choices[currentQuestionIndex][2]);
                    ansD.setVisibility(View.GONE);
                    break;
                }
//
            }
        }

        void finishQuiz () {
            String passStatus = " ";
            int max = Math.max(a1 , Math.max(a2, Math.max(a3, Math.max(a4, Math.max(a5, Math.max(a6,Math.max(a7, Math.max(a8, a9)))))))) ;
             if (max == a1){
                 passStatus = "المستوى هو مابين 2-1";
             }else if( max ==a2 ){
                  passStatus = "المستوى هو مابين 3-2";
             }else if( max ==a3 ){
                   passStatus = "المستوى هو مابين 4-3";
             }else if( max ==a4 ){
                   passStatus = "المستوى هو مابين 5-4";
             }else if( max ==a5 ){
                   passStatus = "المستوى هو مابين 6-5";
             }else if( max ==a6 ){
                   passStatus = "المستوى هو مابين 7-6";
             }else if( max ==a7){
                    passStatus = "المستوى هو مابين 8-7";
             }else if( max ==a8){
                   passStatus = "المستوى هو مابين 9-8";
             }else if( max ==a9 ){
                   passStatus = "المستوى هو مابين 10-9";
             }


            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("  " + passStatus + "من 10" )
                    .setPositiveButton("إعادة", (dialog, i) -> restartQuiz())
                    .setCancelable(false)
                    .show();
        }

        void restartQuiz () {
            selectedAnswer = " ";
            currentQuestionIndex = 0;
            a1=0 ; a2=0; a3=0; a4=0; a5=0; a6=0; a7=0; a8=0; a9 =0;
            loadNewQuestion();
        }




    public void menu(){


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(GravityCompat.START);

                ansA.setVisibility(View.GONE);
                ansB.setVisibility(View.GONE);
                ansC.setVisibility(View.GONE);
                ansD.setVisibility(View.GONE);
                submitBtn.setVisibility(View.GONE);


            }
        });

        Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (currentQuestionIndex) {

                    case 0:

                    case 1:

                    case 2:

                    case 3: {
                        ansA.setVisibility(View.VISIBLE);
                        ansB.setVisibility(View.VISIBLE);
                        ansC.setVisibility(View.GONE);
                        ansD.setVisibility(View.GONE);
                        submitBtn.setVisibility(View.VISIBLE);
                        break;
                    }

                    case 4:
                    case 6: {
                        ansA.setVisibility(View.VISIBLE);
                        ansB.setVisibility(View.VISIBLE);
                        ansC.setVisibility(View.VISIBLE);
                        ansD.setVisibility(View.VISIBLE);
                        submitBtn.setVisibility(View.VISIBLE);

                        break;
                    }

                    case 5: {
                        ansA.setVisibility(View.VISIBLE);
                        ansB.setVisibility(View.VISIBLE);
                        ansC.setVisibility(View.VISIBLE);
                        ansD.setVisibility(View.GONE);
                        submitBtn.setVisibility(View.VISIBLE);

                        break;
                    }
//
                }
//


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

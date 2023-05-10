package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class aboutUs extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);


        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });



        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter. getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    finish();
                }
            }
        });




        menu();


    }



    private void setupOnboardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemUs = new OnboardingItem();
        itemUs.setTitle("من نحن ؟");
        itemUs.setDescription("يهدف تطبيق أتكئ الى تسهيل الارادة والتعايش مع هذا المرض من خلال التواصل مع الاطباء المختصين او المرضى الذين يعانون من نفس المرض بغرض الدردشة والترفيه والتخفيف على بعضهم البعض و يتيح أيضًا التواصل مع اشخاص اخرين للتحسين من صحتهم النفسية نحن هنا لـ بناء مجتمعات تدعم وترعى مرضى التصلب المتعدد نهدف لتعزيز الرعاية الذاتية والحياة الصحية مع المرض.");
        itemUs.setImage(R.drawable.p_us);


        OnboardingItem itemChat = new OnboardingItem();
        itemChat.setTitle("دردشات");
        itemChat.setDescription("بامكانك التواصل وتبادل الرسائل مع أعضاء اتكئ.");
        itemChat.setImage(R.drawable.p_chat);


        OnboardingItem itemExersice = new OnboardingItem();
        itemExersice.setTitle("تمارين");
        itemExersice.setDescription("نقدم بعض التمارين التي قد تساهم في تحسين الصحة الجسدية للمريض.");
        itemExersice.setImage(R.drawable.p_exersice);


        OnboardingItem itemTest = new OnboardingItem();
        itemTest.setTitle("تحديد مستوى المرض");
        itemTest.setDescription(" نطرح بعض الاسئلة التي بامكانها ان تحدد مستوى مرض المريض.");
        itemTest.setImage(R.drawable.p_test);

        OnboardingItem itemReminders = new OnboardingItem();
        itemReminders.setTitle("تَذكيرات بمواعيدك");
        itemReminders.setDescription("يمكنك اضافة مواعيدك بسرعة وكتابة ملاحظاتك.");
        itemReminders.setImage(R.drawable.p_reminders);


        OnboardingItem itemPositive = new OnboardingItem();
        itemPositive.setTitle("كُن ايجابيا");
        itemPositive.setDescription("بودكاست ولقاءات تدعم الصحة النفسية للمريض.");
        itemPositive.setImage(R.drawable.p_positive);



        onboardingItems.add(itemUs);
        onboardingItems.add(itemChat);
        onboardingItems.add(itemExersice);
        onboardingItems.add(itemTest);
        onboardingItems.add(itemReminders);
        onboardingItems.add(itemPositive);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);

    }




    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }


    private void setCurrentOnboardingIndicator(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setText("الذهاب للصفحةالرئيسية");
        } else {
            buttonOnboardingAction.setText("التالي");
        }
    }




    public void menu(){

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
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
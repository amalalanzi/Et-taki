package com.example.ettaki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ettaki extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ettaki);

        if(SharedPrefManager.getInstance(this).isLoggedin()){
            finish();
            startActivity(new Intent(this , MainActivity2.class));
        }


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
                    startActivity(new Intent(getApplicationContext(), login.class));
                    finish();
                }
            }
        });


    }

    private void setupOnboardingItems() {

        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemUs = new OnboardingItem();
        itemUs.setTitle("من نحن ؟");
        itemUs.setDescription("يهدف تطبيق أتكئ الى تسهيل الارادة والتعايش مع مرض التصلب المتعدد، نهدف لتعزيز الرعاية الذاتية والحياة الصحية مع المرض.");
      //  itemUs.setDescription("يهدف تطبيق أتكئ الى تسهيل الارادة والتعايش مع هذا المرض من خلال التواصل مع الاطباء المختصين او المرضى الذين يعانون من نفس المرض بغرض الدردشة والترفيه والتخفيف على بعضهم البعض و يتيح أيضًا التواصل مع اشخاص اخرين للتحسين من صحتهم النفسية نحن هنا لـ بناء مجتمعات تدعم وترعى مرضى التصلب المتعدد نهدف لتعزيز الرعاية الذاتية والحياة الصحية مع المرض.");
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
            buttonOnboardingAction.setText("ابدأ");
        } else {
            buttonOnboardingAction.setText("التالي");
        }
    }

}
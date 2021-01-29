package info.androidhive.introslider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;
    CardView cvYes,cvNo;
    int customColor = 0x239CBC;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    ArrayList<String> symptomsList;
    TextView mainQuestionTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeStatusBarColor();
        mViewPager = findViewById(R.id.viewPager);

        symptomsList = new ArrayList<>();

        cvYes = findViewById(R.id.cvYes);
        cvNo = findViewById(R.id.cvNo);

        dotsLayout = (LinearLayout) findViewById(R.id.questionsLayoutDots);
        mainQuestionTV = findViewById(R.id.mainQuestionTV);


        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.qt_1));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_2));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_3));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_4));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_5));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_6));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_7));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_8));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_9));
        mCardAdapter.addCardItem(new CardItem(R.string.qt_10));

        addBottomDots(0);



        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(10);


        cvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int current = mViewPager.getCurrentItem()+1;



               mViewPager.setCurrentItem(current);
                if (current < mCardAdapter.getCount()) {
                    // move to next screen
                    addBottomDots(current);
                    symptomsList.add("1");
                }else{
                    if(symptomsList.contains("1")){
                        Log.d("Danger", "Virus Yes: ");
                        startActivity(new Intent(getApplicationContext(), Main_symptoms_result.class));
                    }else{
                        Log.d("Danger", "No Virus Yes: ");
                    }
                }

                Log.d("TAG", "onClick: "+symptomsList);
            }
        });
        cvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = mViewPager.getCurrentItem()+1;


                mViewPager.setCurrentItem(current);
                if (current < mCardAdapter.getCount()) {
                    // move to next screen
                    addBottomDots(current);
                    symptomsList.add("2");
                }else{
                    if(symptomsList.contains("1")){
                        startActivity(new Intent(getApplicationContext(), Main_symptoms_result.class));
                        Log.d("Danger", "Virus Yes: ");
                    }else{
                        Log.d("Danger", "No Virus Yes: ");
                    }
                }
            }
        });
    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.appbarColor));
        }
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[mCardAdapter.getCount()];

        int[] colorsActive =  getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText("∘");
            dots[i].setTextSize(50);
            dots[i].setTextColor(this.getResources().getColor(R.color.dot_dark_screen1));
            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(this.getResources().getColor(R.color.dot_dark_screen1));
        dots[currentPage].setText("●");
        dots[currentPage].setTextSize(40);
        dots[currentPage].setPadding(0, 00, 0, 10);
    }
    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }
}
package com.DvirDev.AlizasGame;

import android.media.Image;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager screenPager;
    ViewPagerAdapter viewPagerAdapter;
    List<ScreenItem> mList;
    List<ScreenItem> questionList;
    Button nextBtn;
    TextView turn,question,title;
    View consLayout;
    Image markQuestion;
    int position2 = 1;
    TabLayout tabIdicator;
    private LinearLayout mLinearLayout;
    /* Listener */
    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        private int jumpPosition = -1;

        @Override
        public void onPageScrolled(int position,
                                   float positionOffset,
                                   int positionOffsetPixels) {
            // We do nothing here.
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                // prepare to jump to the last page
                jumpPosition = viewPagerAdapter.getRealCount();
//                if (position2 > mList.size()){
//                    position2 = screenPager.getCurrentItem();
//                }

                //TODO: indicator.setActive(adapter.getRealCount() - 1)
            } else if (position == viewPagerAdapter.getRealCount() + 1) {
                //prepare to jump to the first page
                jumpPosition = 1;

                //TODO: indicator.setActive(0)
            } else {
                //TODO: indicator.setActive(position - 1)
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //Let's wait for the animation to complete then do the jump.
            if (jumpPosition >= 0
                    && state == ViewPager.SCROLL_STATE_IDLE) {
                // Jump without animation so the user is not
                // aware what happened.
                screenPager.setCurrentItem(jumpPosition, false);
                // Reset jump position.
                jumpPosition = -1;
            }
        }
    };
    private MyPageIndicator mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //make the activity fullscreen

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // hide action bar
        getSupportActionBar().hide();

        // ini views
        screenPager = findViewById(R.id.screen_viepager);
        screenPager.addOnPageChangeListener(listener);
        turn = findViewById(R.id.turnIndicator);
        question = findViewById(R.id.question);
        title = findViewById(R.id.slideTitle);
        consLayout = findViewById(R.id.layout);
        nextBtn = findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenPager.beginFakeDrag();
                screenPager.fakeDragBy(-150.0f);
                screenPager.endFakeDrag();

                //TODO: Find a better method to change pages with onClick method

//                if (position2 <= mList.size()){
//                    position2++;
//                    screenPager.setCurrentItem(position2);
//                }

                if (turn.getText()=="התור שלה" || turn.getText()=="נשים קודם!") {
                    turn.setText("התור שלו");
//                    turn.setTextColor(0xDCB3B3B3);
//                    title.setTextColor(0xDCB3B3B3);
//                    question.setTextColor(0xDCB3B3B3);
                    consLayout.setBackgroundColor(0x9E039BE5);
                } else if (turn.getText()=="התור שלו") {
                    turn.setText("התור שלה");
//                    turn.setTextColor(0xDC202020);
//                    title.setTextColor(0xDC202020);
//                    question.setTextColor(0xDC202020);
                    consLayout.setBackgroundColor(0x86D81B60);

                }
            }
        });
        tabIdicator = findViewById(R.id.tab_indicator);

        turn.setText("נשים קודם!");

        questionList = new ArrayList<>();
        questionList.add(new ScreenItem("אוכל!","מה המאכל האהוב עליך\\ עלייך?"));
        questionList.add(new ScreenItem("סוד...","ספר על דבר שאף אחד לא יודע עליך"));
        questionList.add(new ScreenItem("עקרונות","על אילו שלושה עקרונות לא היית מוותר\\ת?"));
        questionList.add(new ScreenItem("גאווה","ספר\\י על מעשה שאת\\ה יכול\\ה להיות גאה בו"));
        questionList.add(new ScreenItem("עתיד>>","איפה את\\ה רואה את עצמך בעוד 10 שנים?"));
        mList = new ArrayList<>(questionList);


//        screenPager = findViewById(R.id.screen_viepager);
        viewPagerAdapter = new ViewPagerAdapter(this,mList);
        screenPager.setAdapter(viewPagerAdapter);
//        screenPager.setCurrentItem(1, false);

        tabIdicator.setupWithViewPager(screenPager);

        //testing new approach to deal with the page indicator
        mLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);
        mIndicator = new MyPageIndicator(this, mLinearLayout, screenPager, R.drawable.indicator_selector);
        mIndicator.setPageCount(mList.size());
        mIndicator.show();

    }

    //todo:think of a way to make the questions random
/*
    public void removeQuestion(int pos){
        questionList.remove(pos);

        for (ScreenItem i:mList
             ) {

        }
    }

    public void initmList(){
        mList = new ArrayList<>();
        mList.add(questionList.get(0));
        mList.add(questionList.get(1));
        mList.add(questionList.get(2));
    }

    public void swipeLeftReorder(){

    }
*/
    // setup viepager

}

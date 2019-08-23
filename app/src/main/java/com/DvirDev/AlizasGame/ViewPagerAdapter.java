package com.DvirDev.AlizasGame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Random;

public class ViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<ScreenItem> mListScreen;

    public ViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    /*@Override
    public int getCount() {
        return mListScreen.size();
    }*/

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    /*
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        Random rand = new Random();
        int n = rand.nextInt(mListScreen.size());

        TextView title = layoutScreen.findViewById(R.id.slideTitle);
        TextView question = layoutScreen.findViewById(R.id.question);
        title.setText(mListScreen.get(n).getTitle());
        question.setText(mListScreen.get(n).getQuestion());

        container.addView(layoutScreen);

        return layoutScreen;
    }*/

    @Override
    public int getCount() {
        // Since we want to put 2 additional pages at left & right,
        // the actual size will plus 2.
        return mListScreen.size() == 0 ? 0 : mListScreen.size() + 2;
    }

    public int getRealCount() {
        return mListScreen.size();
    }

    // Random method
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//
//        Random rand = new Random();
//        int n = rand.nextInt(mListScreen.size());
////        int modelPosition = mapPagerPositionToModelPosition(position);
//
////        Object model = mListScreen.get(modelPosition);
//        Object model = mListScreen.get(n);
//
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
//        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);
//
//
//
//        TextView title = layoutScreen.findViewById(R.id.slideTitle);
//        TextView question = layoutScreen.findViewById(R.id.question);
////        title.setText(mListScreen.get(n).getTitle());
////        question.setText(mListScreen.get(n).getQuestion());
//
//        title.setText(((ScreenItem) model).Title);
//        question.setText(((ScreenItem) model).question);
//
//        container.addView(layoutScreen);
//
//        return layoutScreen;
//    }


    //Non-random method

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int modelPosition = mapPagerPositionToModelPosition(position);

        Object model = mListScreen.get(modelPosition);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        TextView title = layoutScreen.findViewById(R.id.slideTitle);
        TextView question = layoutScreen.findViewById(R.id.question);

        title.setText(((ScreenItem) model).Title);
        question.setText(((ScreenItem) model).question);

        container.addView(layoutScreen);

        return layoutScreen;
    }


    private int mapPagerPositionToModelPosition(int pagerPosition) {
        // Put last page model to the first position.
        if (pagerPosition == 0) {
            return getRealCount() - 1;
        }
        // Put first page model to the last position.
        if (pagerPosition == getRealCount() + 1) {
            return 0;
        }
        return pagerPosition - 1;
    }


}



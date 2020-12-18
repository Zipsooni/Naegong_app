package com.example.naegong_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewpageAdapter extends FragmentStateAdapter {
    public int mCount;
    public ViewpageAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        int index = getRealPosition(position);
        if(index == 0)
            return new Hometab();
        else if(index == 1)
            return new Hometab2();
        else
            return new Hometab3();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public int getRealPosition(int position) {
        return position % mCount;
    }
}


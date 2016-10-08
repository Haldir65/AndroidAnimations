package com.me.harris.androidanimations._04_customView.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._04_customView.fragment.canvasMainFragment;
import com.me.harris.androidanimations.databinding.ActivityCanvasBinding;

/**
 * Created by Harris on 2016/9/19.
 */

public class CustomViewActivity extends AppCompatActivity {

    ActivityCanvasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.frameLayout, canvasMainFragment.newInstance()).
                commit();

    }


    public void setToolBarTitle(String title) {
        binding.toolbar.setTitle(title);
    }


}
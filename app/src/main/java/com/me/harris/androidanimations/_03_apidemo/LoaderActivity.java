package com.me.harris.androidanimations._03_apidemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations.databinding.ActivityLoaderBinding;

/**
 * Created by Fermi on 2016/10/2.
 */

public class LoaderActivity extends AppCompatActivity {
    ActivityLoaderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader);

    }
}
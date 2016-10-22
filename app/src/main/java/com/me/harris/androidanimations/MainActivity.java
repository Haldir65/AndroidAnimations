package com.me.harris.androidanimations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.me.harris.androidanimations._01_drawable.activity.DrawableMainActivity;
import com.me.harris.androidanimations._02_surfaceview.SurfaceViewActivity;
import com.me.harris.androidanimations._03_apidemo.BouncingBalls;
import com.me.harris.androidanimations._11_Loader.LoaderActivity;
import com.me.harris.androidanimations._03_apidemo.MaterialWitness;
import com.me.harris.androidanimations._03_apidemo.ShadowCardDrag;
import com.me.harris.androidanimations._03_apidemo.ViewDragHelperActivity;
import com.me.harris.androidanimations._03_apidemo.YoutubeActivity;
import com.me.harris.androidanimations._04_customView.activity.CustomViewActivity;
import com.me.harris.androidanimations._05_animation.DrawableAnimationActivity;
import com.me.harris.androidanimations._05_animation.PathAnimationActivity;
import com.me.harris.androidanimations._05_animation.PropertyAnimationActivity;
import com.me.harris.androidanimations._05_animation.ViewAnimationActivity;
import com.me.harris.androidanimations._07_permission.MarshmallowPermissionActivity;
import com.me.harris.androidanimations._08_coordinateLayout.CoordinateLayoutEntrance;
import com.me.harris.androidanimations._09_recyclerView.adapter.MainAdapter;
import com.me.harris.androidanimations._09_recyclerView.itemDecoration.MainAdapterItemDecoration;
import com.me.harris.androidanimations._10_statusBar.StatusBarActivity_Activity_one;
import com.me.harris.androidanimations.databinding.ActivityMainBinding;
import com.me.harris.androidanimations.interfaces.GenericCallBack;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()  //主线程上调用File.exists()方法就会出error Log
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAdapter = new MainAdapter<Pair<String, Class>>(R.layout.item_main, new GenericCallBack<Pair<String, Class>>() {
            @Override
            public void onClick(View view, Pair<String, Class> stringClassPair) {
                Intent intent = new Intent(MainActivity.this, stringClassPair.second);
                startActivity(intent);

            }
        });
        List<Pair<String, Class>> list = new ArrayList<>();
        list.add(new Pair<String, Class>("ViewAnimation", ViewAnimationActivity.class));
        list.add(new Pair<String, Class>("DrawableAnimation", DrawableAnimationActivity.class));
        list.add(new Pair<String, Class>("PropertyAnimation", PropertyAnimationActivity.class));
        list.add(new Pair<String, Class>("PathAnimation", PathAnimationActivity.class));
        list.add(new Pair<String, Class>("BouncingBalls", BouncingBalls.class));
        list.add(new Pair<String, Class>("ShadowCard", ShadowCardDrag.class));
        list.add(new Pair<String, Class>("CardImageView", MaterialWitness.class));
        list.add(new Pair<String, Class>("CustomView", CustomViewActivity.class));
        list.add(new Pair<String, Class>("ViewDragHelper", ViewDragHelperActivity.class));
        list.add(new Pair<String, Class>("YoutubeActivity", YoutubeActivity.class));
        list.add(new Pair<String, Class>("LoaderActivity", LoaderActivity.class));
        list.add(new Pair<String, Class>("DrawableMainActivity", DrawableMainActivity.class));
        list.add(new Pair<String, Class>("Permission", MarshmallowPermissionActivity.class));
        list.add(new Pair<String, Class>("CoordinateLayout", CoordinateLayoutEntrance.class));
        list.add(new Pair<String, Class>("StatusBar", StatusBarActivity_Activity_one.class));
        list.add(new Pair<String, Class>("SurfaceView", SurfaceViewActivity.class));
        mAdapter.setmDatas(list);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.addItemDecoration(new MainAdapterItemDecoration(this));
        binding.toolbar.setTitle(getClass().getSimpleName());
    }


}
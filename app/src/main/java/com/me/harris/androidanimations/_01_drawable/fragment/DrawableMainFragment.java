package com.me.harris.androidanimations._01_drawable.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.harris.androidanimations.R;
import com.me.harris.androidanimations._01_drawable.activity.DrawableMainActivity;
import com.me.harris.androidanimations.databinding.FragmentCanvasBinding;
import com.me.harris.androidanimations.interfaces.GenericCallBack;
import com.me.harris.androidanimations.ui.recyclerview.adapter.MainAdapter;
import com.me.harris.androidanimations.ui.recyclerview.itemDecoration.MainAdapterItemDecoration;
import com.me.harris.androidanimations.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fermi on 2016/10/8.
 */

public class DrawableMainFragment extends Fragment {
    FragmentCanvasBinding binding;

    private MainAdapter mAdapter;

    public static DrawableMainFragment newInstance() {
        Bundle args = new Bundle();
        DrawableMainFragment fragment = new DrawableMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_canvas, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setAdapter(mAdapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerView.addItemDecoration(new MainAdapterItemDecoration(getActivity()));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainAdapter<>(R.layout.item_main, new GenericCallBack<Pair<String, Class<Fragment>>>() {
            @Override
            public void onClick(View view, Pair<String, Class<Fragment>> stringClassPair) {
                Class<Fragment> fragmentClass = stringClassPair.second;
                Fragment fragment = null;
                try {
                    fragment = fragmentClass.newInstance();//调用无参数的、私有构造函数
                } catch (java.lang.InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                getFragmentManager().beginTransaction()
                        .addToBackStack(fragmentClass.getSimpleName()).
                        replace(R.id.frameLayout, fragment).commit();
                ((DrawableMainActivity) getActivity()).setToolBarTitle(fragmentClass.getSimpleName());
            }
        });
        List<Pair<String, Class>> mDatas = new ArrayList<>();
        mDatas.add(new Pair<String, Class>("BitmapDrawable", BitmapDrawableFragment.class));
        mDatas.add(new Pair<String, Class>("ShapeDrawable", ShapeDrawableFragment.class));
        mDatas.add(new Pair<String, Class>("LayerDrawable", LayerDrawableFragment.class));
        mAdapter.setmDatas(mDatas);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.w("");
    }
}
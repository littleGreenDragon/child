package com.example.child.model;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.child.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class ThirdFragment extends BlankFragment {

    private ImageView mHBack;
    private ImageView mHHead;
    private TextView mUserName;

    private ItemView mNickName;
    private ItemView mSex;
    private ItemView mPass;
    private ItemView mAbout;
    public ThirdFragment() {
        // Required empty public constructor
    }

    public static ThirdFragment newInstance(String param1) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        mHBack = (ImageView) rootView.findViewById(R.id.h_back);
        mHHead = (ImageView) rootView.findViewById(R.id.h_header);
        mUserName = (TextView) rootView.findViewById(R.id.user_name);
        //下面item控件
        mNickName = (ItemView) rootView.findViewById(R.id.nickName);
        mSex = (ItemView) rootView.findViewById(R.id.sex);
        mPass = (ItemView) rootView.findViewById(R.id.pass);
        mAbout = (ItemView) rootView.findViewById(R.id.about);
        //设置背景磨砂效果
        Glide.with(getContext()).load(R.mipmap.header)
                .bitmapTransform(new BlurTransformation(getContext(), 100), new CenterCrop(getContext()))
                .into(mHBack);
        //设置圆形图像
        Glide.with(getContext()).load(R.mipmap.header)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mHHead);

        //设置用户名整个item的点击事件
        mNickName.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        mSex.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        mPass.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        mAbout.setItemClickListener(new ItemView.itemClickListener() {
            @Override
            public void itemClick(String text) {
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
package com.example.child.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.child.R;
import com.example.child.model.BlankFragment;
import com.example.child.model.FirstFragment;
import com.example.child.model.SecondFragment;
import com.example.child.model.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class TitleBottomActivity extends AppCompatActivity {
    protected ViewPager content;
    protected RadioGroup tabs;
    protected List<BlankFragment> fragments;
    protected FragmentPagerAdapter mAdapter;
    protected Toolbar title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListen();
    }
    protected void init()
    {
        content = (ViewPager) findViewById(R.id.fragment_vp);
        tabs = findViewById(R.id.tabs_rg);
        fragments = new ArrayList<>();
        fragments.add(FirstFragment.newInstance("首页"));
        fragments.add(SecondFragment.newInstance("亲密度"));
        fragments.add(ThirdFragment.newInstance("我的"));
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        content.setAdapter(mAdapter);
        title = (Toolbar) findViewById(R.id.titleBar);
        title.setTitle("首页");
        setSupportActionBar(title);
    }
    protected void addListen()
    {
        content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //写滑动页面的代码
                RadioButton radioButton = (RadioButton) tabs.getChildAt(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        //写点击底部导航栏事件
                            content.setCurrentItem(i);
                            title.setTitle(fragments.get(i).getMContentText());

                        return;
                    }
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_add:
                //写添加代码
                Toast.makeText(TitleBottomActivity.this, "增加", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_menu_settings:
                //写设置代码
                Toast.makeText(TitleBottomActivity.this, "设置", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
    private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<BlankFragment> mList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<BlankFragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return this.mList == null ? null : this.mList.get(position);
        }

        @Override
        public int getCount() {
            return this.mList == null ? 0 : this.mList.size();
        }
    }
}
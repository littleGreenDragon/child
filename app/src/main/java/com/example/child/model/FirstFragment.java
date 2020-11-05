package com.example.child.model;

import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.child.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirstFragment extends BlankFragment {
    private StaggeredGridView gridView ;
    private List<String> descriptions;
    private List<Integer> pictureResources;


    private boolean mHasRequestedMore;

    public FirstFragment() {
    }

    public static FirstFragment newInstance(String param1) {
        FirstFragment fragment = new FirstFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        descriptions = new ArrayList<>();
        pictureResources = new ArrayList<>();
        pictureResources.add(R.mipmap.picture_one);
        pictureResources.add(R.mipmap.picture_two);
        pictureResources.add(R.mipmap.picture_three);
        pictureResources.add(R.mipmap.picture_four);
        pictureResources.add(R.mipmap.picture_six);
        gridView = rootView.findViewById(R.id.grid_view);
        List<HashMap<String,Object>> listItem = new ArrayList<>();
        for (int i = 0;i<30;i++)
        {
            HashMap<String,Object> map = new HashMap<>();
            descriptions.add(String.valueOf("来自XXX省份的XXX小朋友画的XXXX,给予美好的希望,真切的感情,希望XXXXXXXXX"+i));
            map.put("describe",descriptions.get(i));
            map.put("picture",pictureResources.get(i%5));
            listItem.add(map);
        }

        ListAdapter adapter = new SimpleAdapter(
                getContext(),
                listItem,
                R.layout.item,
                new String[]{"describe","picture"},
                new int[]{R.id.item_description,R.id.item_picture});
        gridView.setAdapter(adapter);
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
                Log.d("TAG", "onScroll firstVisibleItem:" + firstVisibleItem +
                        " visibleItemCount:" + visibleItemCount +
                        " totalItemCount:" + totalItemCount);
                // our handling
                if (!mHasRequestedMore) {
                    int lastInScreen = firstVisibleItem + visibleItemCount;
                    if (lastInScreen >= totalItemCount) {
                        Log.d("TAG", "onScroll lastInScreen - so load more");
                        mHasRequestedMore = true;
                        onLoadMoreItems();
                    }
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Item Clicked: "+i,Toast.LENGTH_LONG).show();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Item Long Clicked: " + i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return rootView;
    }
    private void onLoadMoreItems() {
        mHasRequestedMore = false;
    }

}
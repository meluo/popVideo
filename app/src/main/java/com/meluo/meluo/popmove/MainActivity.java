package com.meluo.meluo.popmove;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.meluo.meluo.popmove.adapter.ImageAdapter;
import com.meluo.meluo.popmove.entity.VideoInfo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private GridView gridView;
    private List<VideoInfo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();
        gridView=(GridView)findViewById(R.id.gridView);
        ImageAdapter imageAdapter=new ImageAdapter(this,list);
        gridView.setAdapter(imageAdapter);
    }

}

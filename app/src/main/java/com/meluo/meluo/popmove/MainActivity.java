package com.meluo.meluo.popmove;

import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meluo.meluo.popmove.adapter.ImageAdapter;
import com.meluo.meluo.popmove.entity.VideoInfo;
import com.meluo.meluo.popmove.utils.Config;
import com.meluo.meluo.popmove.utils.NUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private List<VideoInfo> list;
    ImageAdapter imageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=new ArrayList<>();
        listView=(ListView)findViewById(R.id.gridView);

    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus) {
            int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
            imageAdapter = new ImageAdapter(this, list, contentTop);
            listView.setAdapter(imageAdapter);
            loadData();
        }
    }

    private void loadData() {
        NUtils.get(NUtils.TYPE_TXT, Config.API_URL,new NUtils.AbsCallback(){
            @Override
            public void response(String url, byte[] bytes) {

                try {
                    String json=new String(bytes,"utf-8");
                    TypeToken<List<VideoInfo>> typeToken=new TypeToken<List<VideoInfo>>(){};
                    String s=new JSONObject(json).getJSONArray("results").toString();
                    List<VideoInfo> l=new Gson().fromJson(s,typeToken.getType());
                    list.addAll(l);
                    imageAdapter.notifyDataSetChanged();

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}

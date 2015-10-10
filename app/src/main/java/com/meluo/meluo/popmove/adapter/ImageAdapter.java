package com.meluo.meluo.popmove.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.meluo.meluo.popmove.entity.VideoInfo;

import java.util.List;

/**
 * Created
 * Author:meluo
 * Email:kongmuo@126.com
 * Date:15-10-10
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<VideoInfo> list;

    public ImageAdapter(Context context, List<VideoInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(convertView);


        return null;
    }
}

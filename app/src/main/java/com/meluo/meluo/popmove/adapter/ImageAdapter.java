package com.meluo.meluo.popmove.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.meluo.meluo.popmove.R;
import com.meluo.meluo.popmove.entity.VideoInfo;
import com.meluo.meluo.popmove.utils.Config;
import com.squareup.picasso.Picasso;

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

    private int mheight;
    private int mwidth;
    public ImageAdapter(Context context, List<VideoInfo> list,int top) {
        this.context = context;
        this.list = list;


        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

         mheight= (wm.getDefaultDisplay().getHeight()-top)/2;
         mwidth=wm.getDefaultDisplay().getWidth()/2;
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
        View view =null;
        if(convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.image_view_item,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.imageView=(ImageView)view.findViewById(R.id.img1);
            viewHolder.imageView2=(ImageView)view.findViewById(R.id.img2);
            view.setTag(viewHolder);

        }else{
            view=convertView;
        }


        ViewHolder v= (ViewHolder) view.getTag();

        Picasso.with(context).load(Config.BASE_URL+list.get(position).getBackdrop_path())
                .resize(mwidth,mheight)
                .centerCrop()
                .into(v.imageView);
        Picasso.with(context).load(Config.BASE_URL+list.get(position).getPoster_path())
                .resize(mwidth,mheight)
                .centerCrop()
                .into(v.imageView2);
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        ImageView imageView2;
    }
}

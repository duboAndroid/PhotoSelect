package com.example.dubo.myActivity.baseAadapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dubo.my_begin_test.R;

import java.util.List;

/**
 * Created by
 */

public class ImageAdapter extends BaseAdapter {
    private Activity activity;
    private List<String> pathList;
    public ImageAdapter(Activity activity,List<String> pathList) {
        this.activity = activity;
        this.pathList = pathList;
    }
    /* private List<String> paths;

        public myAdapter(List<String> paths) {
            this.paths = paths;
        }*/

    @Override
    public int getCount() {
        return pathList.size();
    }

    @Override
    public Object getItem(int i) {
        return pathList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view==null) {
            view = View.inflate(activity, R.layout.item_img, null);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        Glide.with(activity).load(pathList.get(i)).into(holder.imageView);
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            imageView= (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
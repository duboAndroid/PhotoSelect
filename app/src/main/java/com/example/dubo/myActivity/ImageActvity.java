package com.example.dubo.myActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dubo.myActivity.baseAadapter.ImageAdapter;
import com.example.dubo.my_begin_test.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import imageActvity.ImgSelActivity;
import imgsel.ImageLoader;
import imgsel.ImgSelConfig;

/**
 * Created by
 */
public class ImageActvity  extends Activity {
    private static final int REQUEST_CODE = 0;
    GridView gridview;
    private TextView tvResult;
    private List<String> pathList;
    ImageAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.image_activity);
        gridview= (GridView) findViewById(R.id.gridview);
        tvResult = (TextView) findViewById(R.id.tvResult);
        if(null == pathList)pathList = new ArrayList<>();
        myAdapter=new ImageAdapter(this,pathList);
        gridview.setAdapter(myAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> allPath = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            // 测试Fresco。可不理会
            // draweeView.setImageURI(Uri.parse("file://"+pathList.get(0)));
            if(null != allPath){
                for (String path : allPath) {
                    //tvResult.append(path + "\n");     //添加路径
                    pathList.add(path);
                }
                myAdapter.notifyDataSetChanged();
            }else{
                System.out.print("=========allPath is null===============>");
            }
        }
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    public void Multiselect(View view) {
        tvResult.setText("");
        ImgSelConfig config = new ImgSelConfig.Builder(loader).multiSelect(true)
                .statusBarColor(Color.parseColor("#3F51B5")).build(); // 使用沉浸式状态栏
        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

    public void Single(View view) {
        tvResult.setText("");
        ImgSelConfig config = new ImgSelConfig.Builder(loader)
                // 是否多选
                .multiSelect(false)
                // 确定按钮背景色
                .btnBgColor(Color.GRAY)
                // 确定按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 返回图标ResId
                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)//
                //.backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .title("图片")
                .titleColor(Color.WHITE)
                .titleBgColor(Color.parseColor("#3F51B5"))
                .cropSize(1, 1, 200, 200)
                .needCrop(false)
                // 第一个是否显示相机
                .needCamera(true)
                // 最大选择图片数量
                .maxNum(9)
                .build();

        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }
}

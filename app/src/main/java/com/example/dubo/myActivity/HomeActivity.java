package com.example.dubo.myActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dubo.my_begin_test.R;

import ui.textHorseUi;


/**
 * Created by
 */
public class HomeActivity extends Activity {
    private ImageView obj_pic;
    private textHorseUi my_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);
        obj_pic = (ImageView) findViewById(R.id.obj_pic);
        my_phone = (textHorseUi) findViewById(R.id.my_phone);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(obj_pic, "rotationY", 0, 45, 90, 135, 180, 225, 270, 315, 360);//中心旋转
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        objectAnimator.setDuration(4000);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.start();

        my_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ImageActvity.class);
                startActivity(intent);
            }
        });
    }
}
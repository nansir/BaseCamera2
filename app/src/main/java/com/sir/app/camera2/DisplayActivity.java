package com.sir.app.camera2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Created by zhuyinan on 2018/7/27.
 * Contact by 445181052@qq.com
 */
public class DisplayActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);

        ImageView imageView = findViewById(R.id.iv_image);
        findViewById(R.id.btn_return).setOnClickListener(this);

        String filepath = getIntent().getExtras().getString("filepath", null);

        File file = new File(filepath);

        Glide.with(this).load(file).into(imageView);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

package com.example.thaherm.postagraf;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FrameLayout canvas = (FrameLayout) findViewById(R.id.canvasView);
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        StickerImageView iv_sticker = new StickerImageView(PhotoActivity.this);
        iv_sticker.setImageBitmap(bmp);
        iv_sticker.setRotation(30);
//        iv_sticker.setMove_orgX(30);
//        iv_sticker.setMove_orgY(30);
        canvas.addView(iv_sticker);

        Log.i("CANVAS ",String.valueOf(canvas.getChildCount()) );

//        StickerImageView v1 = (StickerImageView)canvas.getChildAt(0);
//        v1.setRotation(20);

//        iv_sticker.getRotate_newX() // USE THIS

    }

}

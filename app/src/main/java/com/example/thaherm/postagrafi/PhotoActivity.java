package com.example.thaherm.postagrafi;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class PhotoActivity extends AppCompatActivity {
    Bitmap bmp;
    ImageView img;
//    StickerImageView iv_sticker;
    FrameLayout canvas;
    Portgraf port ;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        canvas = (FrameLayout) findViewById(R.id.canvasView);
        canvas.setDrawingCacheEnabled(true);
        canvas.buildDrawingCache();
        img = (ImageView) findViewById(R.id.img);
        but = (Button) findViewById(R.id.but);
        port = new Portgraf();
        Bitmap ii = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.img);
        img.setImageBitmap(ii);



        Singat sig = new Singat();
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        sig.setByteArray(byteArray);
        port.getSignatureList().add(sig);

        AddPort(port);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share();
            }
        });





//        iv_sticker = new StickerImageView(PhotoActivity.this);
//        iv_sticker.setImageBitmap(bmp);
//        canvas.addView(iv_sticker);

//        buttom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StickerImageView x =  (StickerImageView)canvas.getChildAt(1);
//                ViewGroup.LayoutParams lp = x.getLayoutParams();
//                int height = lp.height;
//                int width = lp.width;
//                ViewGroup.LayoutParams lp2 = new ViewGroup.LayoutParams(width,height);
//
//
//                StickerImageView iv_sticker2 =new StickerImageView(PhotoActivity.this);
//                iv_sticker2.setImageBitmap(x.getImageBitmap());
//                iv_sticker2.setRotation(x.getRotation());
//                iv_sticker2.setX(x.getX());
//                iv_sticker2.setY(x.getY());
//                canvas.addView(iv_sticker2,lp2);
//
//
//
//
//            }
//        });



//scaling with params
// rotation with get rotation
        // need position


//        move_orgX 586.4577 centerx 394.4577
//       move move_orgY 1091.0 centery 687.0

        Log.i("CANVAS ",String.valueOf(canvas.getChildCount()) );

//        StickerImageView v1 = (StickerImageView)canvas.getChildAt(0);
//        v1.setRotation(20);

//        iv_sticker.getRotate_newX() // USE THIS

    }

    private void AddPort(Portgraf port) {

        for(Singat sig : port.getSignatureList())
        {
            StickerImageView iv_sticker = new StickerImageView(PhotoActivity.this);
            iv_sticker.setImageBitmap(sig.getBitmap());
            canvas.addView(iv_sticker);


        }



           }

           public void Share (){

               Bitmap bm = null;

               bm = canvas.getDrawingCache();


               try {
                   ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                   bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                   String path = MediaStore.Images.Media.insertImage(getContentResolver(), bm, "Title", null);
                   Uri imageUri = Uri.parse(path);

                   @SuppressWarnings("unused")

                   Intent waIntent = new Intent(Intent.ACTION_SEND);
                   waIntent.setType("image/*");
                   waIntent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
                   startActivity(Intent.createChooser(waIntent, "Share with"));
               } catch (Exception e) {
                   Log.e("Error on sharing", e + " ");
                   Toast.makeText(this, "App not Installed", Toast.LENGTH_SHORT).show();
               }


           }

}

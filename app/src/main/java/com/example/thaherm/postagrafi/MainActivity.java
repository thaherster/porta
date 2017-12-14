package com.example.thaherm.postagrafi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    SignaturePad mSignaturePad;
    private enum color  {BLACK, WHITE, YELLOW,ORANGE,RED,PINK,VIOLET,BLUE,AQUA,GREEN }
    private Button cancel , add,aqua_button,black_button,white_button,yellow_button,orange_button,red_button,pink_button,violet_button,blue_button,green_button;
    private ImageButton brush, color, clear;
    private SeekBar seekbrush;
    private LinearLayout seekbrushlayout;
    private LinearLayout colorlayout;
    private TextView seektext;
    private int sce=1;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        cancel = (Button) findViewById(R.id.cancel);
        brush = (ImageButton) findViewById(R.id.brush);
        color = (ImageButton) findViewById(R.id.color);
        clear = (ImageButton) findViewById(R.id.clear);
        add = (Button) findViewById(R.id.add);
        black_button = (Button) findViewById(R.id.black_button);
        white_button = (Button) findViewById(R.id.white_button);
        yellow_button = (Button) findViewById(R.id.yellow_button);
        orange_button = (Button) findViewById(R.id.orange_button);
        red_button = (Button) findViewById(R.id.red_button);
        pink_button = (Button) findViewById(R.id.pink_button);
        violet_button = (Button) findViewById(R.id.violet_button);
        blue_button = (Button) findViewById(R.id.blue_button);
        aqua_button = (Button) findViewById(R.id.turquice_button);
        green_button = (Button) findViewById(R.id.green_button);
        seekbrush = (SeekBar) findViewById(R.id.seekbrush);
        seekbrushlayout = (LinearLayout) findViewById(R.id.seekbrushlayout);
        colorlayout = (LinearLayout) findViewById(R.id.colorlayout);
        seektext = (TextView) findViewById(R.id.seektext);
        seekbrushlayout.setVisibility(View.GONE);
        colorlayout.setVisibility(View.GONE);

        mSignaturePad.setMinWidth(3);
        mSignaturePad.setMaxWidth(7);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    112);
            return;
        }

        seekbrush.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress/5;
                sce =progress/10+1;
                 s =String.valueOf(progress)+"---"+String.valueOf(sce);
                seektext.setText(s);
                mSignaturePad.setMinWidth(progress+(3*sce));
                mSignaturePad.setMaxWidth(progress+(7*sce));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        brush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seekbrushlayout.getVisibility()==View.VISIBLE)
                {
                    seekbrushlayout.setVisibility(View.GONE);
                    colorlayout.setVisibility(View.GONE);

                }
                else {
                    seekbrushlayout.setVisibility(View.VISIBLE);
                    colorlayout.setVisibility(View.GONE);


                }

            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorlayout.getVisibility()==View.VISIBLE)
                {
                    seekbrushlayout.setVisibility(View.GONE);
                    colorlayout.setVisibility(View.GONE);

                }
                else {
                    seekbrushlayout.setVisibility(View.GONE);
                    colorlayout.setVisibility(View.VISIBLE);


                }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.clear();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Bitmap bmp = mSignaturePad.getTransparentSignatureBitmap() ;
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();

                Intent anotherIntent = new Intent(MainActivity.this, PhotoActivity.class);
                anotherIntent.putExtra("image", byteArray);
                startActivity(anotherIntent);
                finish();

            }
        });


        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

            @Override
            public void onStartSigning() {
                //Event triggered when the pad is touched
                seekbrushlayout.setVisibility(View.GONE);
                colorlayout.setVisibility(View.GONE);
            }

            @Override
            public void onSigned() {
                //Event triggered when the pad is signed
            }

            @Override
            public void onClear() {
                //Event triggered when the pad is cleared
            }
        });

        black_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.BLACK);

            }
        });  white_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.WHITE);


            }
        });  yellow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.YELLOW);


            }
        });  orange_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.LTGRAY);


            }
        });  red_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.RED);


            }
        });  pink_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.MAGENTA);


            }
        });  violet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.LTGRAY);



            }
        });  blue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.BLUE);


            }
        });
 aqua_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.CYAN);


            }
        });
 green_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.setPenColor(Color.GREEN);


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 112) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
            } else {
                // User refused to grant permission.
            }
        }
    }
}


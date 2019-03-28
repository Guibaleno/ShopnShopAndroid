package com.example.a533.shopnshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class photoActivity extends AppCompatActivity {
ImageView imageView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);

        Button btncamera=findViewById(R.id.button);
        Button btngallery=findViewById(R.id.button2);
        imageView =findViewById(R.id.imageVieww);
        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture  =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(takePicture ,0);

            }
        });
        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);

            }
        });

    }
}

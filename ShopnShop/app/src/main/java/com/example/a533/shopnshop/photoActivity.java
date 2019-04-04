package com.example.a533.shopnshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import java.net.URI;


public class photoActivity extends AppCompatActivity {
ImageView imageView;
    private static final int RESULT_LOAD_IMAGE =100;
    Uri imageuir;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imageVieww);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
        else
        {
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
             imageView.setImageBitmap(bitmap);
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
        imageuir=data.getData();
        imageView.setImageURI(imageuir);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);

        Button btncamera=findViewById(R.id.button_takePicture);
        Button btngallery=findViewById(R.id.button_gallery);
        imageView =findViewById(R.id.imageVieww);

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture  =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(takePicture ,0);
//                AlertDialog.Builder builder = new AlertDialog.Builder(photoActivity.this);
//                builder.setMessage("Voulez-vous prendre une nouvelle photo?");
//                builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent takePicture  =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(takePicture ,0);
//                    }
//                });
//
//                builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                    }
//                });
//                AlertDialog alert = builder.create();
//                alert.show();
            }
        });
        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto ,RESULT_LOAD_IMAGE);



            }
        });

    }
}

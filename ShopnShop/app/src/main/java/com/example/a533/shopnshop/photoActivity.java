package com.example.a533.shopnshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;


public class photoActivity extends AppCompatActivity {
    ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 100;
    Uri imageuir;
    private SQLite dbShop;

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
        } else {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            imageuir = data.getData();
            imageView.setImageURI(imageuir);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        dbShop = new SQLite(this);
        Button btncamera = findViewById(R.id.button_takePicture);
        Button btngallery = findViewById(R.id.button_gallery);
        Button btnSave = findViewById(R.id.button_enregistrerImage);
        imageView = findViewById(R.id.imageVieww);

        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);

            }
        });
        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, RESULT_LOAD_IMAGE);

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                imageView.invalidate();
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                 Bitmap bitmap = drawable.getBitmap();

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
                byte[] byteArray = os.toByteArray();

             //   dbShop.
                dbShop.InsertPhoto("allo00",Base64.encodeToString(byteArray, 0).toString());
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Cursor items = dbShop.getPhoto("allo00");

                if (items.getCount() != 0) {
                    items.moveToNext();
                        Toast.makeText(getApplicationContext(), items.getString(0), Toast.LENGTH_LONG).show();

                        byte[] decodedString = Base64.decode(items.getString(0), Base64.DEFAULT);
                    imageView.setImageBitmap( BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

                }



            }
        });
    }

}

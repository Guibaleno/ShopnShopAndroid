package com.example.a533.shopnshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class photoActivity extends AppCompatActivity {
    ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 100;
    Uri imageuir;
    private SQLite dbShop;
    Toolbar myToolbar;


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
        Intent i = getIntent();
        final String allo = i.getStringExtra("User");
        AfficherphotoDeProfil(allo);

        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        btncamera.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                         //    try {
                                                 startActivityForResult(takePicture, 0);
                                         //    }

                                         //    catch (SecurityException aa) {

                                                 Toast.makeText(getApplicationContext(), "Activivé l'appareil photo", Toast.LENGTH_SHORT).show();


                                           //  }
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
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                byte[] byteArray = os.toByteArray();
                dbShop.InsertPhoto(allo, Base64.encodeToString(byteArray, 0).toString());
                Toast.makeText(getApplicationContext(), "Votre photo a été enregistrée", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Deconnexion();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // ...
//
        // Define the listener
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when action item collapses
                return true;  // Return true to collapse action view
            }
            //
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }


        };

        // Get the MenuItem for the action item
        MenuItem actionMenuItem = menu.findItem(R.id.toolbar);
//
        // Assign the listener to that action item

        //actionMenuItem.setOnActionExpandListener(MenuItem.OnActionExpandListener);
//
        // Any other things you have to do when creating the options menu...
//
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Disconnection.getDisconnection() == true) {
            this.finish();
        }

    }


    private void AfficherphotoDeProfil(String User) {
        Cursor items = dbShop.getPhoto(User);
        if (items.getCount() != 0) {
            items.moveToNext();
            byte[] decodedString = Base64.decode(items.getString(0), Base64.DEFAULT);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

        }
    }

    private void Deconnexion(){
        this.finish();
        Disconnection.setDisconnection(true);
    }

}

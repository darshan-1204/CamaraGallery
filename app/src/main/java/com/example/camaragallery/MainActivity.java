package com.example.camaragallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button camara, gallery;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camara = findViewById(R.id.camara);
        gallery = findViewById(R.id.gallery);
        image = findViewById(R.id.image);


        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takephoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takephoto, 0);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pickphoto = new Intent(Intent.ACTION_GET_CONTENT);
                pickphoto.setType("image/*");
                startActivityForResult(pickphoto, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK)
        {
            if(requestCode == 0)
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            }

            if(requestCode == 1)
            {
                Uri uri = data.getData();
                image.setImageURI(uri);
            }
        }
    }
}
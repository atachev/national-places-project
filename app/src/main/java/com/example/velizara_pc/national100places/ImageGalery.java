package com.example.velizara_pc.national100places;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ImageGalery extends AppCompatActivity {

    String scanPath;
    File[] allFiles;
    Button nextButton;
    Button prevButton;
    ImageView viewPhoto;
    int fileNumb;
    int currentFile = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_galery);
        File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/ObjectsPictures/");
        allFiles = folder.listFiles();
        fileNumb=allFiles.length;
        viewPhoto = findViewById(R.id.galeryView);
        Bitmap myBitmap = BitmapFactory.decodeFile(allFiles[0].getAbsolutePath());
        viewPhoto.setImageBitmap(myBitmap);
        nextButton = findViewById(R.id.ImageGaleryButtonNext);
        prevButton = findViewById(R.id.ImageGaleryButtonPrev);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFile++;
                if(currentFile == fileNumb)
                    currentFile = 0;
                Bitmap myBitmap = BitmapFactory.decodeFile(allFiles[currentFile].getAbsolutePath());
                viewPhoto.setImageBitmap(myBitmap);
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFile--;
                if(currentFile<0)
                    currentFile = fileNumb - 1;
                Bitmap myBitmap = BitmapFactory.decodeFile(allFiles[currentFile].getAbsolutePath());
                viewPhoto.setImageBitmap(myBitmap);
            }
        });
    }
}


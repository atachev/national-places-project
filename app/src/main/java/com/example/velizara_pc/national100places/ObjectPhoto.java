package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ObjectPhoto extends AppCompatActivity {

    private int photo;

    int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_photo);
        final ImageView obectPhoto = (ImageView) findViewById(R.id.object_pic);
        Button prev = (Button) findViewById(R.id.prev);
        Button next = (Button) findViewById(R.id.next);
        Intent receiveObjectPhoto = getIntent();
        photo = (int) receiveObjectPhoto.getSerializableExtra("photos");
        obectPhoto.setImageResource(photo);
        obectPhoto.setImageLevel(level);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level--;
                if(level < 0)
                    level = 3;
                obectPhoto.setImageLevel(level);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level++;
                if(level > 3)
                    level = 0;
                obectPhoto.setImageLevel(level);
            }
        });
    }
}

package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class ObectInfo extends AppCompatActivity {

    private TextView infooutput;
    private StringBuilder obectInfo = new StringBuilder();
    private StringBuilder workHours = new StringBuilder();
    private StringBuilder tax = new StringBuilder();
    private StringBuilder contact = new StringBuilder();
    private StringBuilder gps = new StringBuilder();
    private String fileName;
    private CheckBox visited;
    private RatingBar rating;
    private float currentRating;
    private TextView ratingValue;
    private int photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obect_info);

        BufferedReader reader = null;
        Intent receiveObjectInfo = getIntent();
        fileName = receiveObjectInfo.getStringExtra("obectName");
        photo = receiveObjectInfo.getIntExtra("photos", 0);
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
            String line;
            while(true){
                line = reader.readLine();
                if(line.equals("end"))
                    break;
                obectInfo.append(line);
                obectInfo.append('\n');
            }
            while(true){
                line = reader.readLine();
                if(line.equals("end"))
                    break;
                workHours.append(line);
                workHours.append('\n');
            }
            while(true){
                line = reader.readLine();
                if(line.equals("end"))
                    break;
                tax.append(line);
                tax.append('\n');
            }
            while(true){
                line = reader.readLine();
                if(line.equals("end"))
                    break;
                contact.append(line);
                contact.append('\n');
            }
            while(true){
                line = reader.readLine();
                if(line.equals("end"))
                    break;
                gps.append(line);
                gps.append('\n');
            }
            visited = (CheckBox) findViewById(R.id.visited);
            boolean isVisited = NavigationMenu.visitedTable.get(fileName);
            visited.setChecked(isVisited);
            rating = (RatingBar)findViewById(R.id.ratingBar);
            currentRating = NavigationMenu.ratingTable.get(fileName);
            rating.setRating(currentRating);
            ratingValue = (TextView)findViewById(R.id.ratingValue);
            ratingValue.setText("рейтинг" +" " + String.format("%.2f", currentRating));
            infooutput = (TextView)findViewById(R.id.obect_info);
            infooutput.setText((CharSequence) obectInfo);
            Button bWork = (Button) findViewById(R.id.work_hours);
            Button bTax = (Button) findViewById(R.id.ent_tax);
            Button bContact = (Button) findViewById(R.id.contacts);
            Button bGPS = (Button) findViewById(R.id.GPS);
            Button objectPhoto = (Button) findViewById(R.id.photoGalery);
            bWork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendWorkHours = new Intent(view.getContext(), AdditionalInfo.class);
                    sendWorkHours.putExtra("index", "wHours");
                    sendWorkHours.putExtra("wHours", workHours.toString());
                    startActivity(sendWorkHours);
                }
            });
            bTax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendTax = new Intent(view.getContext(), AdditionalInfo.class);
                    sendTax.putExtra("index", "entTax");
                    sendTax.putExtra("entTax", tax.toString());
                    startActivity(sendTax);
                }
            });
            bContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendContact = new Intent(view.getContext(), AdditionalInfo.class);
                    sendContact.putExtra("index", "contact");
                    sendContact.putExtra("contact", contact.toString());
                    startActivity(sendContact);
                }
            });
            bGPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendGPS = new Intent(view.getContext(), AdditionalInfo.class);
                    sendGPS.putExtra("index", "gpscoord");
                    sendGPS.putExtra("gpscoord", gps.toString());
                    startActivity(sendGPS);
                }
            });
            objectPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendObjectPhoto = new Intent(view.getContext(), ObjectPhoto.class);
                    sendObjectPhoto.putExtra("photos", photo);
                    startActivity(sendObjectPhoto);
                }
            });
            rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    FileOutputStream fos = null;
                    DataOutputStream dos = null;

                    currentRating = (currentRating + v)/2;
                    ratingBar.setRating(currentRating);
                    ratingValue.setText("рейтинг" + " " + String.format("%.2f", currentRating));
                    NavigationMenu.ratingTable.put(fileName, currentRating);
                    File ratings = new File(getApplicationContext().getFilesDir(), "objectRating1");
                    try {
                        fos = new FileOutputStream(ratings);
                        dos = new DataOutputStream(fos);
                        for (Map.Entry<String, Float> rating : NavigationMenu.ratingTable.entrySet()) {
                            String key = rating.getKey();
                            int keyLenght = key.length();
                            dos.writeInt(keyLenght);
                            dos.writeChars(key);
                            dos.writeFloat(rating.getValue());
                        }
                    } catch (IOException io) {
                        Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                    }finally {
                        if(dos != null){
                            try{
                                dos.close();
                            }catch (IOException e){
                                Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                            }
                        }
                        if(fos != null){
                            try{
                                fos.close();
                            }catch (IOException e){
                                Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Грешка при четене на файла", Toast.LENGTH_LONG).show();
        }
        finally {
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
    public void visitedToggle(View view){

        FileOutputStream fos = null;
        DataOutputStream dos = null;

        boolean isChecked = visited.isChecked();
        NavigationMenu.visitedTable.put(fileName, isChecked);
        File userName = new File(getApplicationContext().getFilesDir(), LoginActivity.userFileName);
        try {
            fos = new FileOutputStream(userName);
            dos = new DataOutputStream(fos);
            for (Map.Entry<String, Boolean> visited : NavigationMenu.visitedTable.entrySet()) {
                String key = visited.getKey();
                int keyLenght = key.length();
                dos.writeInt(keyLenght);
                dos.writeChars(key);
                dos.writeBoolean(visited.getValue());
            }
        } catch (IOException io) {
            Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
        }finally {
            if(dos != null){
                try{
                    dos.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if(fos != null){
                try{
                    fos.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}

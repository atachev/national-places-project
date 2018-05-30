package com.example.velizara_pc.national100places;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginB;
    private Button regB;
    private Intent intent;
    private FileInputStream fis;
    private DataInputStream dis;
    public static HashMap<String, Users> hmUser = new HashMap<>();
    private Users user;
    public static String userFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        File path = getApplicationContext().getFilesDir();
        File usersData = new File(path, "Users Database");
        try {
            fis = new FileInputStream(usersData);
            dis = new DataInputStream(fis);
            while (dis.available() > 0) {
                user = new Users();
                user.setUsername(readString(dis.readInt()));
                user.setPassword(readString(dis.readInt()));
                user.setEmail(readString(dis.readInt()));
                hmUser.put(user.getUsername(), user);
            }
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }
            }
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            loginB = (Button) findViewById(R.id.loginButt);
            regB = (Button) findViewById(R.id.regButt);
            loginB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateData(username.getText().toString(), password.getText().toString());
                }
            });

            regB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public boolean validateEmail(String email) {
        String emailPatt = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        Pattern pattern = Pattern.compile(emailPatt);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        String passPatt = "(?=.*[a-z])(?=.*[0-9]).{6,}";
        Pattern pattern = Pattern.compile(passPatt);
        Matcher matcher =pattern.matcher(password);
        return matcher.matches();
    }

    public void validateData(String un, String pass) {
        if(un.isEmpty() || pass.isEmpty()) {
            username.setError("Въведете потребителско име!");
            password.setError("Въведете парола!");
        }

        if(!hmUser.containsKey(un))
            Toast.makeText(getApplicationContext(), "Невалиднo потребителско име!", Toast.LENGTH_SHORT).show();
        else if(validatePassword(pass)) {
            String storedPass = hmUser.get(un).getPassword();
            if(storedPass.equals(pass)){
                userFileName = un;
                intent = new Intent(LoginActivity.this, NavigationMenu.class);
                //intent.putExtra("user", un);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Невалидна парола!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Невалидна парола!", Toast.LENGTH_SHORT).show();
        }
    }

    public String readString(int count) {

        String key = "";

        for(int i = 0; i<count; i++) {
            try {
                key = key + dis.readChar();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key;
    };

}
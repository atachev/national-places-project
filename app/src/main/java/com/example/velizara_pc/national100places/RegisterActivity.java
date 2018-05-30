package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText userR;
    EditText emailR;
    EditText passwR;
    EditText repPassR;
    Button regButt;
    Users user;
    FileOutputStream fos;
    DataOutputStream dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userR = (EditText)findViewById(R.id.usernameR);
        emailR = (EditText)findViewById(R.id.email);
        passwR = (EditText)findViewById(R.id.passR);
        repPassR = (EditText)findViewById(R.id.passRepR);
        regButt = (Button)findViewById(R.id.regButtR);
        regButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData(userR.getText().toString(), emailR.getText().toString(), passwR.getText().toString(), repPassR.getText().toString());
            }
        });
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

    public void validateData(String un, String email, String pass, String passR) {
        if(un.isEmpty() || email.isEmpty() || pass.isEmpty() || passR.isEmpty()) {
            userR.setError("Въведете потребителско име!");
            emailR.setError("Въведете email!");
            passwR.setError("Въведете парола!");
            repPassR.setError("Повторете паролата!");
        }

        if(LoginActivity.hmUser.containsKey(un))
            Toast.makeText(getApplicationContext(), "Потребителското име съществува. Въведете ново!", Toast.LENGTH_SHORT).show();
        else if(validateEmail(email) && validatePassword(pass) && validatePassword(passR)) {
            if(passR.equals(pass)) {
                user = new Users(un,email,pass);
                //user.setUsername(un);
                //user.setEmail(email);
                //user.setPassword(pass);
                //db.addUser(user);
                LoginActivity.hmUser.put(un, user);
                File path = getApplicationContext().getFilesDir();
                File userData = new File(path, "Users Database");
                try{
                    fos = new FileOutputStream(userData);
                    dos = new DataOutputStream(fos);
                    for(Map.Entry<String, Users> currUser: LoginActivity.hmUser.entrySet()){
                        dos.writeInt(currUser.getValue().getUsername().length());
                        dos.writeChars(currUser.getValue().getUsername());
                        dos.writeInt(currUser.getValue().getPassword().length());
                        dos.writeChars(currUser.getValue().getPassword());
                        dos.writeInt(currUser.getValue().getEmail().length());
                        dos.writeChars(currUser.getValue().getEmail());
                    }
                }catch(IOException io){
                    Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                }finally {
                    if (dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                        }
                    }
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Грешка при затваряне на файла", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                Toast.makeText(getApplicationContext(),"Успешно регистриране!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, NavigationMenu.class);
                //intent.putExtra("user", un);
                LoginActivity.userFileName = un;
                startActivity(intent);
                //db.close();
            } else {
                Toast.makeText(getApplicationContext(), "Паролите не съвпадат!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Невалидна парола!", Toast.LENGTH_SHORT).show();
        }
    }

}


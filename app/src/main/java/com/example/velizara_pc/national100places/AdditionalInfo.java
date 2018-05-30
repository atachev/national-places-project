package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdditionalInfo extends AppCompatActivity {

    TextView outputInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);
        Intent receiveWorkHour = getIntent();
        String label = receiveWorkHour.getStringExtra("index");
        StringBuilder work_Hours = new StringBuilder(receiveWorkHour.getStringExtra(label));
        outputInfo = (TextView)findViewById(R.id.addInfo);
        outputInfo.setText((CharSequence) work_Hours);
    }
}

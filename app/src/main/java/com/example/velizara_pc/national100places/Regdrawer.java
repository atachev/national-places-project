package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Regdrawer extends AppCompatActivity {

    //DrawerLayout myRegDrawer;
    String[] regList = {"Североизточен", "Югоизточен", "Северен", "Южен", "Северозападен", "Югозападен", "Софийски"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_drawer);
        //myRegDrawer = (DrawerLayout)findViewById(R.id.reg_drawer_layout);
        //myRegDrawer.openDrawer(GravityCompat.START);
        ListView regionList = findViewById(R.id.reg_list);
        ArrayAdapter<String> regions = new ArrayAdapter<String>(this, R.layout.reg_content, R.id.textView,regList);
        regionList.setAdapter(regions);
        regionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent oblastiShow = new Intent(view.getContext(), OblastiList.class);
                oblastiShow.putExtra("izbor", i);
                startActivity(oblastiShow);
            }
        });
    }
}

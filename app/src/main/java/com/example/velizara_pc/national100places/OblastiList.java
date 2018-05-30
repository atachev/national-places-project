package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OblastiList extends AppCompatActivity {

    int choice = 0;
    String [] oblasti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oblasti_list);
        Intent receiveIzbor = getIntent();
        choice = receiveIzbor.getIntExtra("izbor", 0);
        switch (choice) {
            case 0:
                oblasti = new String[]{"Варненска", "Добричка", "Силистренска", "Шуменска"};
                break;
            case 1:
                oblasti = new String[]{"Бургаска", "Сливенска", "Ямболска"};
                break;
            case 2:
                oblasti = new String[]{"Великотърновска", "Габровска", "Ловешка", "Плевенска", "Разградска", "Русенска",
                        "Търговищка"};
                break;
            case 3:
                oblasti = new String[]{"Кърджалийска", "Пазарджишка", "Пловдивска", "Смолянска", "Старозагорска", "Хасковска"};
                break;
            case  4:
                oblasti = new String[]{"Видинска", "Врачанска", "Монтанска"};
                break;
            case 5:
                oblasti = new String[]{"Благоевградска", "Кюстендилска", "Пернишка"};
                break;
            default:
                oblasti = new String[]{"София град", "Софийска"};
                break;
        }
        ListView oblastiView = (ListView) findViewById(R.id.oblastiList);
        ArrayAdapter<String> oblastiAdapter = new ArrayAdapter<String>(this, R.layout.oblasti_content, R.id.oblastName, oblasti);
        oblastiView.setAdapter(oblastiAdapter);
        oblastiView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent sendObecti = new Intent(view.getContext(), Obecti.class);
                sendObecti.putExtra("oblasti", choice);
                sendObecti.putExtra("obect", i);
                startActivity(sendObecti);
            }
        });
    }
}

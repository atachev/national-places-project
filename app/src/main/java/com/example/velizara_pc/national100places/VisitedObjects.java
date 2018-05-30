package com.example.velizara_pc.national100places;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisitedObjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_objects);
        final ArrayList<HashMap<String, String>> visitedObject = new ArrayList<>();
        for(Map.Entry<String, Boolean> visited: NavigationMenu.visitedTable.entrySet() ){
            if(visited.getValue()){
                HashMap<String, String> isVisited = new HashMap<String, String>();
                isVisited.put("name", visited.getKey());
                visitedObject.add(isVisited);
            }
        }
        ListView objectInfo = (ListView)findViewById(R.id.visitedList);
        String [] from = {"name"};
        int [] to = {R.id.obectiName};
        SimpleAdapter showObject = new SimpleAdapter(this, visitedObject, R.layout.obecti_content, from, to);
        objectInfo.setAdapter(showObject);
        objectInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> obect;
                obect = visitedObject.get(i);
                Intent sendObjectInfo = new Intent(view.getContext(), ObectInfo.class);
                sendObjectInfo.putExtra("obectName", obect.get("name"));
                startActivity(sendObjectInfo);
            }
        });
    }
}

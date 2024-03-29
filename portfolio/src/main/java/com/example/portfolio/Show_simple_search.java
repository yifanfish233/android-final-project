package com.example.portfolio;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_simple_search extends AppCompatActivity {

    ListView simple_view;

    private ArrayList<Info_holder>simple_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_simple_search);
        simple_view = (ListView)findViewById(R.id.simple_listview);
        simple_result = getIntent().getParcelableArrayListExtra("simple");
        CustomAdapter_simple customAdapter = new CustomAdapter_simple(this, R.layout.list_item,simple_result);
        simple_view.setAdapter(customAdapter);
        simple_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView tv = (TextView)view;
                String Choosed_Name = customAdapter.getItem(position).getName();
                String Choosed_Info = customAdapter.getItem(position).getinfo();
                Toast.makeText(Show_simple_search.this, "save to your favourite "+Choosed_Name, Toast.LENGTH_SHORT).show();
                //System.out.print(tv.getText()+"\n");
                //System.out.print(customAdapter.getItem(position));
                SharedPreferences.Editor editor = getSharedPreferences("Likedlist",MODE_PRIVATE).edit();
                SharedPreferences pref = getSharedPreferences("Likedlist",MODE_PRIVATE);
                String name = pref.getString("BusinessName","");
                String info = pref.getString("BusinessInfo","");
                if(name.equals(Choosed_Name)){
                    Toast.makeText(Show_simple_search.this, "remove from your favourite "+Choosed_Name, Toast.LENGTH_SHORT).show();
                    pref.edit().clear().commit();
                }else{
                    editor.putString("BusinessName",Choosed_Name);
                    editor.putString("BusinessInfo",Choosed_Info);
                    //System.out.print(customAdapter.getItem(position));
                    editor.commit();
                }


            }
        });

    }







}

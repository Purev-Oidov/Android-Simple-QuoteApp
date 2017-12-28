package com.example.arlequina.slideviewer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arlequina.slideviewer.database.DBHelper;
import com.example.arlequina.slideviewer.model.Quote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;
    private List<Quote> quoteList;
    private DBHelper dbHelper;

    ArrayList arrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DBHelper(this);

        try {
            dbHelper.copyDatabase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dbHelper.openDatabase();
        quoteList = dbHelper.getList();

        arrList = new ArrayList<String>();
        arrList.add("COSMONAUT");
        arrList.add("SATELITE");
        arrList.add("GALAXY");
        arrList.add("ROCKET");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        slideAdapter = new SlideAdapter(this,quoteList);
        viewPager.setAdapter(slideAdapter);
    }


}

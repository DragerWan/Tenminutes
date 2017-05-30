package com.czy.tenminutes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.czy.tenminutes.R;

import java.util.ArrayList;

public class SelectPreference extends AppCompatActivity {
    private String[] buttonId = {"technology","car","sports","entertainment","military","society"};
    private ArrayList<String> favorate = new ArrayList<>();
    private ArrayList<String> like = new ArrayList<>();
    private ArrayList<String> dislike = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preference);
    }

    /*class FavoriteListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Button select = (Button)view;
            select.getScrollBarStyle()
        }
    }*/
}

package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class shareride extends Fragment {

    Button btn_1, btn_2, btn_3, btn_4;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.shareride, container, false);

        btn_1 = myview.findViewById(R.id.btn_1);
        btn_2 = myview.findViewById(R.id.btn_2);
        btn_3 = myview.findViewById(R.id.btn_3);
        btn_4 = myview.findViewById(R.id.btn_4);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new shareride_setdetails()).addToBackStack("").commit();
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new shareride_searchcollege()).addToBackStack("").commit();
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new shareride_location()).addToBackStack("").commit();
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_container, new shareride_previoushistory()).addToBackStack("").commit();
            }
        });

        return myview;
    }

}

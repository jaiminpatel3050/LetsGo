package com.example.myapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class shareride_setdetails extends Fragment {

    SQLiteDatabase db = null;
    EditText txt_location, txt_timetodepart, txt_vehicle_no, txt_contactno, txt_college;
    Button btn_setDetails;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.shareride_setdetails, container, false);

        try {
            db = getActivity().openOrCreateDatabase("LGDb", Context.MODE_PRIVATE, null);
            db.execSQL("create table tbl_share_details (sdID integer primary key autoincrement,location varchar(25),timetodepart varchar(25),vehicle_no varchar(25),contact_no varchar(25),college varchar(25))");

        } catch (Exception e) {
            Toast.makeText(getActivity(), "error " + e.toString(), Toast.LENGTH_SHORT).show();
        }

        txt_location = (EditText) myview.findViewById(R.id.details_one);
        txt_timetodepart = (EditText) myview.findViewById(R.id.details_two);
        txt_vehicle_no = (EditText) myview.findViewById(R.id.details_three);
        txt_contactno = myview.findViewById(R.id.details_four);
        txt_college = (EditText) myview.findViewById(R.id.details_five);


        btn_setDetails = (Button) myview.findViewById(R.id.details_save);

        btn_setDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String txt1 = txt_location.getText().toString();
                    String txt2 = txt_timetodepart.getText().toString();
                    String txt3 = txt_vehicle_no.getText().toString();
                    String txt4 = txt_contactno.getText().toString();
                    String txt5 = txt_college.getText().toString();

                    db.execSQL("insert into tbl_share_details (location,timetodepart,vehicle_no,contact_no,college) values('" + txt1 + "','" + txt2 + "','" + txt3 + "','" + txt4 + "','" + txt5 + "')");
                    Toast.makeText(getActivity(), "Records Saved Successfully....", Toast.LENGTH_SHORT).show();
                    fillclear();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "search error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return myview;
    }

    public void fillclear() {

        txt_location.setText("");
        txt_timetodepart.setText("");
        txt_contactno.setText("");
        txt_college.setText("");
        txt_vehicle_no.setText("");
    }
}

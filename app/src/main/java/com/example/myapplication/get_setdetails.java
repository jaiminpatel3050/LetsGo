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

public class get_setdetails extends Fragment {

    SQLiteDatabase db = null;
    EditText txt_location, txt_collegename, txt_contactno, txt_timetoleave;
    Button btn_setDetails;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.getride_setdetails, container, false);

        try {
            db = getActivity().openOrCreateDatabase("LGDb", Context.MODE_PRIVATE, null);
            db.execSQL("create table tbl_set_details (sdID integer primary key autoincrement,location varchar(25),college varchar(25),contact_no integer,timeleave varchar(25))");

        } catch (Exception e) {
            Toast.makeText(getActivity(), "error " + e.toString(), Toast.LENGTH_SHORT).show();
        }

        txt_location = (EditText) myview.findViewById(R.id.txtloc);
        txt_collegename = (EditText) myview.findViewById(R.id.txtcollege);
        txt_contactno = (EditText) myview.findViewById(R.id.txtcollege);
        txt_timetoleave = (EditText) myview.findViewById(R.id.txttime);

        btn_setDetails = (Button) myview.findViewById(R.id.btnsetDetails);

        btn_setDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String txtloc = txt_location.getText().toString();
                    String txtcoll = txt_collegename.getText().toString();
                    String txtcont = txt_contactno.getText().toString();
                    String txttl = txt_timetoleave.getText().toString();

                    db.execSQL("insert into tbl_set_details (location,college,contact_no,timeleave) values('" + txtloc + "','" + txtcoll + "','" + txtcont + "','" + txttl + "')");
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
        txt_timetoleave.setText("");
        txt_contactno.setText("");
        txt_collegename.setText("");
    }
}

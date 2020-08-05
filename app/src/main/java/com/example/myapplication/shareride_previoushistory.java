package com.example.myapplication;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class shareride_previoushistory extends Fragment {

    ArrayList<String> list;
    final ArrayList<String> players1 = new ArrayList<String>();
    SQLiteDatabase db = null;
    GridView gv;
    ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview= inflater.inflate(R.layout.shareride_previoushistory, container, false);
        gv = (GridView) myview.findViewById(R.id.mygrid_prev);


        try {

            db = getActivity().openOrCreateDatabase("LGDb", MODE_PRIVATE, null);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        try {

            Cursor c1 = db.rawQuery("SELECT * from tbl_share_details where sdID < 3 ", null);

            while (c1.moveToNext()) {

                players1.add(String.valueOf("Person Id : " + c1.getString(0)));
                players1.add(String.valueOf("Location : " + c1.getString(1)));
                players1.add(String.valueOf("Time To Depart : " + c1.getString(2)));
                players1.add(String.valueOf("Vehicle No : " + c1.getString(3)));
                players1.add(String.valueOf("Contact No : " + c1.getString(4)));
                players1.add(String.valueOf("College Name : " + c1.getString(5)));

            }
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players1);
            gv.setAdapter(adapter);

        } catch (Exception e) { }

        return myview;
    }
}

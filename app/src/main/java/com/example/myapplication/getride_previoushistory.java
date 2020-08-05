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

public class getride_previoushistory extends Fragment {

    ArrayList<String> list;
    final ArrayList<String> players = new ArrayList<String>();
    SQLiteDatabase db = null;
    GridView gv;
    ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.getride_previoushistory, container, false);
        gv = (GridView) myview.findViewById(R.id.mygrid);

        try {

            db = getActivity().openOrCreateDatabase("LGDb", MODE_PRIVATE, null);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        try {

            //Cursor c = db.rawQuery("SELECT sdID,college from tbl_share_details where college = '" + search + "'", null);
            Cursor c = db.rawQuery("SELECT * from tbl_set_details where sdID < 3 ", null);
            //Toast.makeText(getActivity(), "in cursor", Toast.LENGTH_LONG).show();

            while (c.moveToNext()) {

                        players.add(String.valueOf("ID --> " + c.getString(0)));
                        players.add(String.valueOf("Location--> " + c.getString(1)));
                        players.add(String.valueOf("College Name --> " + c.getString(2)));
                        players.add(String.valueOf("Contact No --> " + c.getString(3)));
                        players.add(String.valueOf("TIme --> " + c.getString(4)));

            }
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
            gv.setAdapter(adapter);

        } catch (Exception e) { }






        return myview;

    }
}

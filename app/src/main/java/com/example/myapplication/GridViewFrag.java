package com.example.myapplication;


import android.content.Context;
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

public class GridViewFrag extends Fragment {

    final ArrayList<String>
            players = new ArrayList<String>();
    SQLiteDatabase db = null;
    ArrayAdapter<String> adapter;
    GridView gv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_grid_view, container, false);

        try {

            db = getActivity().openOrCreateDatabase("LGDb", Context.MODE_PRIVATE, null);
            gv = (GridView) myview.findViewById(R.id.mygrid);
//            Toast.makeText(getActivity(), "connection is set", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        Cursor c = db.rawQuery("SELECT * FROM tbl_client ", null);
        if (c.getCount() == 0) {

            Toast.makeText(getActivity(), "No records found", Toast.LENGTH_LONG).show();
        }
        // Appending records to a string buffer


        StringBuffer buffer = new StringBuffer();


        while (c.moveToNext()) {

            players.add(String.valueOf("ID --> " + c.getString(0)));
            players.add(String.valueOf("Name -->" + c.getString(1)));
            players.add(String.valueOf("Mobile No. --> " + c.getString(2)));
            players.add(String.valueOf("EMail ID -->" + c.getString(3)));
            players.add(String.valueOf("Address --> " + c.getString(4)));
            players.add(String.valueOf("Password -->" + c.getString(5)));

        }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
        gv.setAdapter(adapter);

        return myview;

    }

}

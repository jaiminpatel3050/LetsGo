package com.example.myapplication;


import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;


public class getride_searchcollege extends Fragment {

    ArrayList<String> list;
    final ArrayList<String> players = new ArrayList<String>();

    ArrayList<String> list1;
    final ArrayList<String> players1 = new ArrayList<String>();

    SQLiteDatabase db = null;
    GridView gv,gv1;
    ArrayAdapter<String> adapter,adapter1;
    EditText txtSearch,txtSearchById;
    Button btnSearch, btnavaPer;
    TextView txtGridSearch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.college, container, false);

        gv = (GridView) myview.findViewById(R.id.mygrid);
       // gv1 = (GridView) myview.findViewById(R.id.mygrid1);
     //   txtSearchById=(EditText)myview.findViewById(R.id.txtSearchID);

        try {

            db = getActivity().openOrCreateDatabase("LGDb", MODE_PRIVATE, null);

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        txtSearch = (EditText) myview.findViewById(R.id.searchView);
        btnSearch = (Button) myview.findViewById(R.id.btnSear);

        btnSearch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String search = txtSearch.getText().toString();
                boolean flag = false;

                try {

                    //Cursor c = db.rawQuery("SELECT sdID,college from tbl_share_details where college = '" + search + "'", null);
                    Cursor c = db.rawQuery("SELECT sdID,college from tbl_share_details ", null);

                    while (c.moveToNext()) {

                        int myid = c.getInt(0);
                        String cole = c.getString(1);
                        Toast.makeText(getActivity(), "Database Name"+cole, Toast.LENGTH_LONG).show();

                        if (cole.equals(search)) {

                            flag = true;
                            players.add(String.valueOf("Person Id : " + c.getString(0)));
                            players.add(String.valueOf("College Name : " + c.getString(1)));
                        }
                    }
                    if (flag != true) {
                        Toast.makeText(getActivity(), "College Name Not Found", Toast.LENGTH_LONG).show();
                    }
                    //showMessage("Student Details", players.toString());
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
                    gv.setAdapter(adapter);

                } catch (Exception e) { }
            }
        });

        txtGridSearch=(TextView)myview.findViewById(R.id.txtget_ride_search);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Get the GridView selected/clicked item text
                   // selectedItem = parent.getItemAtPosition(position).toString();
                    //txtGridSearch.setText("GridView item clicked : " +selectedItem + "\nAt index position : " + position);
                txtGridSearch.setText("GridView item clicked : " +players1.toString()+ " ");
                players1.clear();
            }
        });


        //btnavaPer = (Button) myview.findViewById(R.id.btnAvaPer);

//        btnavaPer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    int regID=Integer.parseInt(txtSearchById.getText().toString());
//                    boolean flag1 = false;
//
//                try {
//
//                    Cursor c1 = db.rawQuery("SELECT * from tbl_share_details ", null);
//                    if (c1.getCount() == 0) {
//                        showMessage("Filter By ID", "No Records Found");
//                    }
//                    //while (c1.moveToNext()) {
//
//                    if(c1.moveToFirst()){
//
//                        int myid = c1.getInt(0);
//
//                        if (myid==regID) {
//
//                            flag1 = true;
//                            players1.add(String.valueOf("Person Id : " + c1.getString(0)));
//                            players1.add(String.valueOf("Location : " + c1.getString(1)));
//                            players1.add(String.valueOf("Time To Depart : " + c1.getString(2)));
//                            players1.add(String.valueOf("Vehicle No : " + c1.getString(3)));
//                            players1.add(String.valueOf("Contact No : " + c1.getString(4)));
//                            players1.add(String.valueOf("College Name : " + c1.getString(5)));
//                        }
//                    }
//                    if (flag1 != true) {
//                        Toast.makeText(getActivity(), "ID Not Found", Toast.LENGTH_LONG).show();
//                    }
//                    try {
//
//                        SmsManager smsManager = SmsManager.getDefault();
//                        smsManager.sendTextMessage(c1.getString(4), null, "Get Ride Details --> " + players1.toString(), null, null);
//                        Toast.makeText(getActivity(), "SMS Sent!...", Toast.LENGTH_LONG).show();
//
//                    } catch (Exception ex) {
//                        Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
//                    }
//
//                    showMessage("Share Ride Details", players1.toString());
//                    players1.clear();
//                    //adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players1);
//                    //gv1.setAdapter(adapter1);
//                } catch (Exception e) {}
//            }
//        });
       return myview;
    }

    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

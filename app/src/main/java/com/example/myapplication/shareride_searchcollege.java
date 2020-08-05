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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;


public class shareride_searchcollege extends Fragment {

    ArrayList<String> list;
    final ArrayList<String> players = new ArrayList<String>();

    ArrayList<String> list1;
    final ArrayList<String> players1 = new ArrayList<String>();

    SQLiteDatabase db = null;
    GridView share_gv1,share_gv2;
    ArrayAdapter<String> adapter,adapter1;
    EditText txtShareSearch,txtShareSearchById;
    Button btnShareSearch, btnSharePerson;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_shareride_searchcollege, container, false);

        share_gv1 = (GridView) myview.findViewById(R.id.sharegrid1);
       // share_gv2 = (GridView) myview.findViewById(R.id.sharegrid2);
        txtShareSearchById=(EditText)myview.findViewById(R.id.txtShareSearchID);

        try {

            db = getActivity().openOrCreateDatabase("LGDb", MODE_PRIVATE, null);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        txtShareSearch = (EditText) myview.findViewById(R.id.txtshareSearch);
        btnShareSearch = (Button) myview.findViewById(R.id.btnShareSearch);

        btnShareSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search = txtShareSearch.getText().toString();
                boolean flag_share = false;

                try {

                    //Cursor c = db.rawQuery("SELECT sdID,college from tbl_share_details where college = '" + search + "'", null);
                    Cursor c = db.rawQuery("SELECT sdID,college from tbl_set_details ", null);

                    while (c.moveToNext()) {

                        /*players.add(String.valueOf("ID --> " + c.getString(0)));
                        players.add(String.valueOf("Location--> " + c.getString(1)));
                        players.add(String.valueOf("College Name --> " + c.getString(2)));
                        players.add(String.valueOf("Contact No --> " + c.getString(3)));
                        players.add(String.valueOf("TIme --> " + c.getString(4)));*/

                        int myid = c.getInt(0);
                        String cole = c.getString(1);
                        //Toast.makeText(getActivity(), "Database Name"+cole, Toast.LENGTH_LONG).show();

                        if (cole.equals(search)) {

                            flag_share = true;
                            players.add(String.valueOf("Person Id : " + c.getString(0)));
                            players.add(String.valueOf("College Name : " + c.getString(1)));
                        }
                    }
                    if (flag_share != true) {
                        Toast.makeText(getActivity(), "College Name Not Found", Toast.LENGTH_LONG).show();
                    }
                    //showMessage("Student Details", players.toString());
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
                    share_gv1.setAdapter(adapter);

                } catch (Exception e) { }
            }
        });

        btnSharePerson = (Button) myview.findViewById(R.id.btnSharePerson);

        btnSharePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int regID=Integer.parseInt(txtShareSearchById.getText().toString());
                boolean flag_share1 = false;

                try {

                    Cursor c1 = db.rawQuery("SELECT * from tbl_set_details ", null);
                    if (c1.getCount() == 0) {
                        showMessage("Filter By ID", "No Records Found");
                    }
                    //while (c1.moveToNext()) {

                    if(c1.moveToFirst()){

                        int myid = c1.getInt(0);
                        /*String loc = c.getString(1);
                        String todp = c.getString(2);
                        String vehno = c.getString(3);
                        String cno = c.getString(4);
                        String college = c.getString(5);*/

                        if (myid==regID) {

                            flag_share1 = true;
                            players1.add(String.valueOf("Person Id : " + c1.getString(0)));
                            players1.add(String.valueOf("Location : " + c1.getString(1)));
                            players1.add(String.valueOf("College Name : " + c1.getString(2)));
                            players1.add(String.valueOf("Contact No : " + c1.getString(3)));
                            players1.add(String.valueOf("Time To Leave : " + c1.getString(4)));
                        }
                    }
                    if (flag_share1 != true) {
                        Toast.makeText(getActivity(), "ID Not Found", Toast.LENGTH_LONG).show();
                    }
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(c1.getString(3), null, "Get Ride Details --> " + players1.toString(), null, null);
                        Toast.makeText(getActivity(), "SMS Sent!...", Toast.LENGTH_LONG).show();
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
                    }
                    showMessage("Get Ride Details", players1.toString());
                    players1.clear();
                    //adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players1);
                    //gv1.setAdapter(adapter1);
                } catch (Exception e) {}
            }
        });
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

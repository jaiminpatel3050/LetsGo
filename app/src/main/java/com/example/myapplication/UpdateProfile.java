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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


public class UpdateProfile extends Fragment {


    SQLiteDatabase db=null;
    EditText tName,tMob,tEmail,tAddress,tPassword;
    Button bUpdate;
    String email="";
    public UpdateProfile() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview= inflater.inflate(R.layout.fragment_update_profile, container, false);
        email= getActivity().getIntent().getExtras().getString("user").toString();
        try {

            db = getActivity().openOrCreateDatabase("LGDb", Context.MODE_PRIVATE, null);
        }
        catch (Exception e){
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        tName=(EditText)myview.findViewById(R.id.txtName);
        tMob=(EditText)myview.findViewById(R.id.txtMob);
        tEmail=(EditText)myview.findViewById(R.id.txtemail);
        tAddress=(EditText)myview.findViewById(R.id.txtAddress);
        tPassword=(EditText)myview.findViewById(R.id.txtpassword);

        Cursor c = db.rawQuery("SELECT Name,Mobile_No,Email,Address,Password FROM tbl_client where Email='"+email+"'", null);

        // Checking if no records found
        if (c.getCount() == 0) {

            Toast.makeText(getActivity(),"No records found",Toast.LENGTH_LONG).show();
        }

        while (c.moveToNext()) {

            tName.setText(c.getString(0));
            tMob.setText(c.getString(1));
            tEmail.setText(c.getString(2));
            tAddress.setText(c.getString(3));
            tPassword.setText(c.getString(4));
        }


        bUpdate=(Button)myview.findViewById(R.id.btnUpdate);

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    db.execSQL("update tbl_client set Name='"+tName.getText().toString()+"', Mobile_No='"+tMob.getText().toString()+"',Password='"+tPassword.getText().toString()+"' where Email='" + email + "'");
                    Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });



        return myview;
    }

}

package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class userDataFrag extends Fragment {

    Button btnU;
    EditText tloc,tdest,txtdeprt,vecdet,metpay,sbname;
    SQLiteDatabase db=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_user_data, container, false);

        try
        {
            db=getActivity().openOrCreateDatabase("LGDb", Context.MODE_PRIVATE,null);
            db.execSQL("create table tbluserSearch (userID integer primary key autoincrement,location varchar(25),destination varchar2(15),depart varchar2(50),vehicle varchar2(50),mpayment varchar2(50),sname varchar2(50))");
            //Toast.makeText(getActivity(), "Table Created successfull", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
              Toast.makeText(getActivity(), "error "+ e.toString(), Toast.LENGTH_SHORT).show();
        }

        btnU=(Button)myview.findViewById(R.id.btnuser);

        tloc=(EditText)myview.findViewById(R.id.txtloc);
        tdest=(EditText)myview.findViewById(R.id.txtdest);
        txtdeprt=(EditText)myview.findViewById(R.id.txtdeprt);
        vecdet=(EditText)myview.findViewById(R.id.txtvech);
        metpay=(EditText)myview.findViewById(R.id.txtpayment);
        sbname=(EditText)myview.findViewById(R.id.txtsname);

        btnU.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                try{

                    String loc=tloc.getText().toString();
                    String dest=tdest.getText().toString();
                    String deprt=txtdeprt.getText().toString();
                    String vehicle=vecdet.getText().toString();
                    String mpayment=metpay.getText().toString();
                    String sn=sbname.getText().toString();

                    if(loc.isEmpty()||dest.isEmpty()||deprt.isEmpty() || vehicle.isEmpty()|| mpayment.isEmpty()|| sn.isEmpty()){

                        tloc.setError("Enter Location");
                        tloc.requestFocus();

                        tdest.setError("Enter Destination");
                        tdest.requestFocus();

                        txtdeprt.setError("Enter Time To Depart");
                        txtdeprt.requestFocus();

                        vecdet.setError("Enter Vehicle Details");
                        vecdet.requestFocus();

                        metpay.setError("Enter Method of Payment");
                        metpay.requestFocus();

                        metpay.setError("Enter Method of Payment");
                        metpay.requestFocus();

                        sbname.setError("Enter Person Name");
                        sbname.requestFocus();
                    }
                    else
                    {
                        db.execSQL("insert into tbluserSearch (location,destination,depart,vehicle,mpayment,sname) values('" + loc + "','" + dest + "','" + deprt + "','" + vehicle + "','" + mpayment + "','" + sn + "') ");
                        Toast.makeText(getActivity(), "Records Saved Successfully....", Toast.LENGTH_SHORT).show();
                        fillclear();
                    }         //   }
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "search error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return myview;
    }
    public void fillclear(){

        tloc.setText("");
        tdest.setText("");
        txtdeprt.setText("");
        vecdet.setText("");
        metpay.setText("");
        sbname.setText("");
    }
}

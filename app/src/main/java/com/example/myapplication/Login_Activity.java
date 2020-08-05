package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    TextView forgot, signup;
    EditText l_name, l_pass;
    Button btnlogin;
    SQLiteDatabase db = null;
    LinearLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgot = findViewById(R.id.fp);
        signup = findViewById(R.id.txtsign);
        btnlogin = findViewById(R.id.btnlogin);
        l_name = findViewById(R.id.name);
        l_pass = findViewById(R.id.pass);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = false;
                String name = l_name.getText().toString();
                String pass = l_pass.getText().toString();


                try {

                    db = getApplicationContext().openOrCreateDatabase("LGDb", MODE_PRIVATE, null);
//                    Toast.makeText(getApplicationContext(), "connection is set", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(Login_Activity.this, e.toString(), Toast.LENGTH_LONG).show();
                }

                Cursor c = db.rawQuery("select UserID, Email, Password from tbl_client", null);

                if (name.equals("admin") && pass.equals("admin")) {

                    //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new GridViewFrag()).addToBackStack("").commit();
                    Intent admin_nav=new Intent(Login_Activity.this,adminNavAct.class);
                    startActivity(admin_nav);
                }


                if (c.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Records Found", Toast.LENGTH_LONG).show();
                }

                if (name.isEmpty() && pass.isEmpty()) {
                    // flag=false;
                    l_name.setError("Enter Username");
                    l_name.requestFocus();
                    l_pass.setError("Enter Password");
                }

                while (c.moveToNext()) {

                        int rid = Integer.parseInt(c.getString(0));
                        String em = c.getString(1);
                        String ps = c.getString(2);

                     if (name.equals(em) && pass.equals(ps)) {

                          flag = true;
                          //startActivity(new Intent(Login_Activity.this, MainActivity.class));
                         Intent myint=new Intent(Login_Activity.this,MainActivity.class);
                         myint.putExtra("user", em);
                         startActivity(myint);
                         //myint.putExtra("rid",rid);
                         finish();
                        // myint.putExtra("pass",ps);
                    }
                }
                if (flag != true) {

                    Toast.makeText(getApplicationContext(), "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, Forgot_pswd_activity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, RegisterFrag.class));
            }
        });


//        try {
//            db = getApplicationContext().openOrCreateDatabase("LGDb", MODE_PRIVATE, null);
//           Toast.makeText(getApplicationContext(), "Connection is successfull...", Toast.LENGTH_LONG).show();
//        } catch (
//                Exception ex) {
//            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
//        }
    }
}

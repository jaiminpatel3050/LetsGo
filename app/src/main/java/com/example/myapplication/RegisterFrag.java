package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFrag extends AppCompatActivity {

    SQLiteDatabase db = null;
    Button btn;
    EditText editText1, editText2, editText3, editText4, editText5, editText6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);

        try {
            db = getApplication().openOrCreateDatabase("LGDb", Context.MODE_PRIVATE, null);
            db.execSQL("create table tbl_client (UserID integer primary key autoincrement,Name varchar(25),Mobile_No integer,Email varchar(25),Address varchar(25),Password varchar(25))");

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error " + e.toString(), Toast.LENGTH_SHORT).show();
        }

        btn = (Button) findViewById(R.id.button7);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String one = editText1.getText().toString();
                    String two = editText2.getText().toString();
                    String three = editText3.getText().toString();
                    String four = editText4.getText().toString();
                    String five = editText5.getText().toString();

                    db.execSQL("insert into tbl_client (Name,Mobile_No,Email,Address,Password) values('" + one + "','" + two + "','" + three + "','" + four + "','" + five + "') ");
                    Toast.makeText(getApplicationContext(), "Records Saved Successfully....", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterFrag.this, Login_Activity.class));
                    fillclear();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "search error" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void fillclear() {

        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
    }
}

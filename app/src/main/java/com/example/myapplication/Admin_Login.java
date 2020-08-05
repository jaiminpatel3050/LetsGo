package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Admin_Login extends Fragment {

    EditText username, password;
    Button adm_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_login, container, false);

        username = view.findViewById(R.id.adm_user);
        password = view.findViewById(R.id.adm_pass);
        adm_login = view.findViewById(R.id.adm_login);

        adm_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adm_user = username.getText().toString();
                String adm_pass = password.getText().toString();

                if (adm_user.equals("admin") && adm_pass.equals("admin")) {
                    getFragmentManager().beginTransaction().replace(R.id.frame_container, new GridViewFrag()).addToBackStack("").commit();
                } else {
                    Toast.makeText(getActivity(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}

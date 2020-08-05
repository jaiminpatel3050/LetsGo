package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RefferFragmant extends Fragment {

    Button btnPay;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview= inflater.inflate(R.layout.fragment_reffer, container, false);

        btnPay=(Button)myview.findViewById(R.id.btnPayment);

        btnPay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new PaymentGateway()).addToBackStack("").commit();
            }
        });
        return myview;
    }
}

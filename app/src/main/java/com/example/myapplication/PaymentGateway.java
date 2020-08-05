package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class PaymentGateway extends Fragment {

    Button btnpay;
    RadioButton rb1,rb2,rb3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_payment_gateway, container, false);

        btnpay=(Button)myview.findViewById(R.id.btnPay);

        rb1=(RadioButton)myview.findViewById(R.id.cdCard);
        rb2=(RadioButton)myview.findViewById(R.id.netBank);
        rb3=(RadioButton)myview.findViewById(R.id.paytm);


        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rb1.isChecked()){

                    Toast.makeText(getActivity(),"Credit/Debit Payment Successfull",Toast.LENGTH_SHORT).show();
                }
                if(rb2.isChecked()){

                    Toast.makeText(getActivity(),"Net Banking Payment Successfull",Toast.LENGTH_SHORT).show();
                }
                if(rb3.isChecked()){

                    Toast.makeText(getActivity(),"PayTM Payment Successfull",Toast.LENGTH_SHORT).show();
                }

            }
        });




        return myview;
    }
}

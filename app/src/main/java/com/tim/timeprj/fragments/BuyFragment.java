package com.tim.timeprj.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gms.test.datetimepicker.time.TimePickerDialog;
import com.tim.timeprj.R;
import com.tim.timeprj.activity.LoginActivity;
import com.tim.timeprj.helper.SessionManager;

import java.util.Calendar;


public class BuyFragment extends Fragment {

    Button logout_btn;
    ImageView iv, iv2;
    SessionManager session;

    public BuyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("test", "ONE");
        session = new SessionManager(getActivity().getApplicationContext());
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_buy, container, false);
        findView(rootView);
        setListener();
        return rootView;
    }

    private void setListener() {
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false, "");
                Intent intent = new Intent(getActivity(),
                        LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void findView(View rootView) {
        logout_btn = (Button) rootView.findViewById(R.id.logout_button);
        iv = (ImageView) rootView.findViewById(R.id.iV);
        iv2 = (ImageView) rootView.findViewById(R.id.iV2);

        Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.tag_schedule);

        iv2.setImageDrawable(d);
//        iv2.setBackgroundColor(0xffffffff);
        iv2.setColorFilter(0xffffffff, PorterDuff.Mode.SRC_IN);
        iv.setImageDrawable(d);
//        iv.setBackgroundColor(0xff1395ba);
        iv.setColorFilter(0xff1395ba, PorterDuff.Mode.SRC_IN);


    }

}

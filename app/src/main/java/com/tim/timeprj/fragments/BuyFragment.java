package com.tim.timeprj.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gms.test.datetimepicker.time.TimePickerDialog;
import com.tim.timeprj.R;
import com.tim.timeprj.activity.LoginActivity;
import com.tim.timeprj.helper.SessionManager;

import java.util.Calendar;


public class BuyFragment extends Fragment {

    Button logout_btn, picker_btn;
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
        picker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        (TimePickerDialog.OnTimeSetListener) getActivity(),
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                tpd.vibrate(false);
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d("TimePicker", "Dialog was cancelled");
                    }
                });
                tpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
            }
        });

    }

    private void findView(View rootView) {
        logout_btn = (Button) rootView.findViewById(R.id.logout_button);
        picker_btn = (Button) rootView.findViewById(R.id.picker_button);
    }

}

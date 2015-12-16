package com.tim.timeprj.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gms.test.datetimepicker.time.TimePickerDialog;
import com.tim.timeprj.R;

import java.util.Calendar;

/**
 * Created by Zeng on 02/11/15.
 */

public class FriendsFragment extends Fragment{
    private final static String TAG = FriendsFragment.class.getSimpleName();
    Button picker_btn;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        findView(rootView);
        setListener();
        // Inflate the layout for this fragment
        return rootView;
    }

    private void setListener() {
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
        picker_btn = (Button) rootView.findViewById(R.id.picker_button);

    }
}

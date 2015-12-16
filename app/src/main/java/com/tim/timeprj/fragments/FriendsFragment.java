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

    }

    private void findView(View rootView) {

    }
}

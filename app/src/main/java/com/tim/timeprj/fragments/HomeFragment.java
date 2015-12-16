package com.tim.timeprj.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tim.timeprj.R;

/**
 * Created by Zeng on 02/11/15.
 */

public class HomeFragment extends Fragment{
    private final static String TAG = HomeFragment.class.getSimpleName();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
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

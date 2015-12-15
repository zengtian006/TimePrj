package com.tim.timeprj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tim.timeprj.R;
import com.tim.timeprj.activity.LoginActivity;
import com.tim.timeprj.activity.SignupActivity;

/**
 * Created by Zeng on 2015/12/14.
 */
public class InitFragment extends Fragment {
    Button login_btn, signup_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_init, container, false);
        findView(rootView);
        setListener();
        return rootView;
    }

    private void setListener() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findView(View rootView) {
        login_btn = (Button) rootView.findViewById(R.id.login_button);
        signup_btn = (Button) rootView.findViewById(R.id.signup_button);

    }
}

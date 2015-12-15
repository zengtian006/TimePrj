package com.tim.timeprj.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.tim.timeprj.R;
import com.tim.timeprj.activity.SignupActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeng on 2015/11/3.
 */
public class SignupPhone2Fragment extends Fragment {
    private static final String TAG = SignupActivity.class.getSimpleName();
    Button btn_sign_up;
    EditText edt_username, edt_password, edt_confirm_password;
    private TextInputLayout inputLayoutName, inputLayoutPassword, inputLayoutConfirmPassword;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Progress dialog
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup_phone_2, container, false);
        btn_sign_up = (Button) rootView.findViewById(R.id.btn_signup);
        edt_username = (EditText) rootView.findViewById(R.id.input_name);
        edt_password = (EditText) rootView.findViewById(R.id.input_password);
        edt_confirm_password = (EditText) rootView.findViewById(R.id.input_confirm_password);

        inputLayoutName = (TextInputLayout) rootView.findViewById(R.id.input_layout_name);
        inputLayoutPassword = (TextInputLayout) rootView.findViewById(R.id.input_layout_password);
        inputLayoutConfirmPassword = (TextInputLayout) rootView.findViewById(R.id.confirm_layout_password);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                String phone = SignupActivity.phoneNo;
                if (submitForm()) {
                    Log.v(TAG, "PHONE: " + phone);
//                    registerUser(username, phone, password);
                }
            }
        });
        return rootView;
    }

    private boolean validateName() {
        if (edt_username.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name_only));
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePassword() {
        if (edt_password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            return false;
        } else if (edt_confirm_password.getText().toString().trim().isEmpty()) {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_password));
            return false;
        } else if (!edt_password.getText().toString().trim().equals(edt_confirm_password.getText().toString().trim())) {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_password_mismatch));
            return false;
        } else {
            inputLayoutConfirmPassword.setErrorEnabled(false);
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }


    private boolean submitForm() {
        if (!validateName()) {
            return false;
        }
        if (!validatePassword()) {
            return false;
        }
        return true;
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

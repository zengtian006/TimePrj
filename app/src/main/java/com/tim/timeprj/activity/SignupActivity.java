package com.tim.timeprj.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tim.timeprj.R;
import com.tim.timeprj.fragments.SignupPhone1Fragment;
import com.tim.timeprj.fragments.SignupPhone2Fragment;

/**
 * Created by Zeng on 2015/11/3.
 */
public class SignupActivity extends AppCompatActivity {

    Integer currentFragment;


    public static Context context;
    public static CoordinatorLayout coordinatorLayout;
    public static FragmentManager fragmentManager;
    public static String phoneNo;
    public static int initView = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = SignupActivity.this;
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        currentFragment = 0;
        displayView(initView);
        initView = 0;
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new SignupPhone1Fragment();
                title = getString(R.string.title_signup_phone);
                break;
            case 1:
                fragment = new SignupPhone2Fragment();
                title = getString(R.string.title_signup_phone);
                break;
            default:
                break;
        }

        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
    }
}

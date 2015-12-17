package com.tim.timeprj.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eruntech.addresspicker.widgets.ChineseAddressPicker;
import com.gms.test.datetimepicker.time.RadialPickerLayout;
import com.gms.test.datetimepicker.time.TimePickerDialog;
import com.tim.timeprj.R;

import java.util.Calendar;

/**
 * Created by Zeng on 2015/12/17.
 */
public class AddScheduleActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private final static String TAG = AddScheduleActivity.class.getSimpleName();

    EditText edt_time;
    ChineseAddressPicker mPicker;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        edt_time = (EditText) findViewById(R.id.input_time);
        mPicker = (ChineseAddressPicker) findViewById(R.id.main_picker);
        mButton = (Button) findViewById(R.id.main_btn);

        edt_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
            }
        });
        edt_time.setFocusable(false);
        edt_time.setClickable(true);
        edt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        AddScheduleActivity.this,
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
                tpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        mPicker.setOnAddressPickerListener(new ChineseAddressPicker.OnAddressPickerListener() {
            @Override
            public void onAddressPicked() {
                String address = null;
                if (mPicker.getProviceName() != null) {
                    address = mPicker.getProviceName();
                    if (mPicker.getCityName() != null) {
                        address += " - " + mPicker.getCityName();
                        if (mPicker.getDistrictName() != null) {
                            address += " - " + mPicker.getDistrictName();
                        }
                    }
                }
                mButton.setText(address);
            }

            @Override
            public void onAddressChanged() {
                String address = null;
                if (mPicker.getProviceName() != null) {
                    address = mPicker.getProviceName();
                    if (mPicker.getCityName() != null) {
                        address += " - " + mPicker.getCityName();
                        if (mPicker.getDistrictName() != null) {
                            address += " - " + mPicker.getDistrictName();
                        }
                    }
                }
                mButton.setText(address);
            }
        });

        mButton.setText(getString(R.string.btn_main_text));
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicker.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.done:
                Intent intent = new Intent();
                intent.putExtra("test", edt_time.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0" + hourOfDayEnd : "" + hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0" + minuteEnd : "" + minuteEnd;
        String time = hourString + ":" + minuteString + " - " + hourStringEnd + ":" + minuteStringEnd;
        edt_time.setText(time);
        Log.v(TAG, "hehe: " + "You picked the following time:" + time);
    }
}

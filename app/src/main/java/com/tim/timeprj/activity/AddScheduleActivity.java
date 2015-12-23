package com.tim.timeprj.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eruntech.addresspicker.widgets.ChineseAddressPicker;
import com.gms.test.datetimepicker.time.RadialPickerLayout;
import com.gms.test.datetimepicker.time.TimePickerDialog;
import com.tim.timeprj.R;
import com.tim.timeprj.fragments.HomeFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Zeng on 2015/12/17.
 */
public class AddScheduleActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private final static String TAG = AddScheduleActivity.class.getSimpleName();

    EditText edt_time, edt_address, edt_item;
    ChineseAddressPicker mPicker;
    TextInputLayout layout_address, layout_item;
    List<String> item_name;
    List<Boolean> item_checked;
    RecyclerView mRecyclerView;
    LinearLayout layout_item_list;
    Button btn_confirm;

//    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        findView();
        setListener();
        setView();
    }

    private void setListener() {
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
//                    address = mPicker.getProviceName();
                    if (mPicker.getCityName() != null) {
//                        address += " - " + mPicker.getCityName();
                        if (mPicker.getDistrictName() != null) {
//                            address += " - " + mPicker.getDistrictName();
                            address = mPicker.getDistrictName();
                        }
                    }
                }
                edt_address.setText(address);
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
                edt_address.setText(address);
            }
        });

        edt_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicker.show();
                layout_item_list.setVisibility(LinearLayout.GONE);
            }
        });

        edt_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_item_list.isShown()) {
                    layout_item_list.setVisibility(LinearLayout.GONE);
                } else {
                    layout_item_list.setVisibility(LinearLayout.VISIBLE);
                    if (mPicker.isShown()) {
                        mPicker.hide();
                    }
                }

            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                for (int i = 0; i < item_checked.size(); i++) {
                    if (item_checked.get(i)) {
                        text += item_name.get(i) + ",";
                    }
                }
                if (!text.isEmpty()) {
                    text = text.substring(0, text.length() - 1);
                }
                edt_item.setText(text);
                layout_item_list.setVisibility(LinearLayout.GONE);
            }
        });
    }

    private void setView() {
        item_name = new ArrayList<String>();
        item_checked = new ArrayList<Boolean>();
        for (int i = 0; i < 10; i++) {
            item_name.add("Item " + String.valueOf(i));
        }
        item_checked.add(false);
        item_checked.add(true);
        item_checked.add(false);
        item_checked.add(false);
        item_checked.add(false);
        item_checked.add(true);
        item_checked.add(false);
        item_checked.add(false);
        item_checked.add(false);
        item_checked.add(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CustomAdapter(AddScheduleActivity.this));

        edt_address.setFocusable(false);
        edt_address.setClickable(true);

        edt_time.setFocusable(false);
        edt_time.setClickable(true);

        edt_item.setFocusable(false);
        edt_item.setClickable(true);

        layout_address.setHint(getString(R.string.btn_main_text));
        layout_item.setHint("Item");
    }

    private void findView() {
        edt_time = (EditText) findViewById(R.id.input_time);
        edt_item = (EditText) findViewById(R.id.input_item);
        mPicker = (ChineseAddressPicker) findViewById(R.id.main_picker);
        edt_address = (EditText) findViewById(R.id.input_address);
        layout_address = (TextInputLayout) findViewById(R.id.input_layout_address);
        layout_item = (TextInputLayout) findViewById(R.id.input_layout_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);
        layout_item_list = (LinearLayout) findViewById(R.id.item_list);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
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


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

        private final LayoutInflater inflater;
        private final Context mContext;

        public CustomAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.mContext = context;
        }

        @Override
        public CustomAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new viewHolder(inflater.inflate(R.layout.check_item, parent, false));
        }

        @Override
        public void onBindViewHolder(CustomAdapter.viewHolder holder, final int position) {
            holder.mName.setText(item_name.get(position));
            holder.chkSelected.setChecked(item_checked.get(position));


            holder.chkSelected.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    item_checked.set(position, cb.isChecked());
                    Log.v(TAG, "Clicked on Checkbox: " + cb.getText() + " is " + cb.isChecked());
                }
            });
        }

        @Override
        public int getItemCount() {
            return item_name == null ? 0 : item_name.size();
        }


        public class viewHolder extends RecyclerView.ViewHolder {
            public TextView mName;
            public CheckBox chkSelected;

            public viewHolder(View itemView) {
                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.item_name);
                chkSelected = (CheckBox) itemView.findViewById(R.id.item_checkBox);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(AddScheduleActivity.this, "click: " + item_name.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}

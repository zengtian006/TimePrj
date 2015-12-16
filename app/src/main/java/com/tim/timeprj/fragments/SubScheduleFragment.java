package com.tim.timeprj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tim.timeprj.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zeng on 2015/12/16.
 */
public class SubScheduleFragment extends Fragment {
    private final static String TAG = SubScheduleFragment.class.getSimpleName();
    RecyclerView rListView;

    static String[] date_name;
    static Integer[] date_img;

    static String[] schedule_count = {
            "1",
            "9",
            "2",
            "3",
            "4",
            "5",
            "7"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        findView(rootView);
        setView();
        return rootView;
    }

    private void setView() {
        loadSchedule();
        rListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rListView.setAdapter(new customAdapter(getActivity()));
    }

    private void loadSchedule() {
        date_name = new String[7];
        date_img = new Integer[7];
        for (int i = 0; i < 7; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, i);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            date_name[i] = formattedDate;
            if (i == 0) {
                date_name[i] += " (today)";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("EEE");
            String dayofWeek = sdf.format(c.getTime());
            if (dayofWeek.equals("Mon")) {
                date_img[i] = R.drawable.monday;
            } else if (dayofWeek.equals("Tue")) {
                date_img[i] = R.drawable.tuesday;
            } else if (dayofWeek.equals("Wed")) {
                date_img[i] = R.drawable.wednesday;
            } else if (dayofWeek.equals("Thu")) {
                date_img[i] = R.drawable.thursday;
            } else if (dayofWeek.equals("Fri")) {
                date_img[i] = R.drawable.friday;
            } else if (dayofWeek.equals("Sat")) {
                date_img[i] = R.drawable.saturday;
            } else if (dayofWeek.equals("Sun")) {
                date_img[i] = R.drawable.sunday;
            }
            Log.v(TAG, "Formate time => " + formattedDate);
            Log.v(TAG, "date of week => " + dayofWeek);
        }
    }

    private void findView(View rootView) {
        rListView = (RecyclerView) rootView.findViewById(R.id.date_list);
    }

    private class customAdapter extends RecyclerView.Adapter<customAdapter.viewHolder> {

        private final LayoutInflater inflater;
        private final Context mContext;
        private final String[] item_name;
        private final String[] item_count;
        private final Integer[] img_id;

        public customAdapter(Context context) {
            // TODO Auto-generated constructor stub
            inflater = LayoutInflater.from(context);
            this.item_name = SubScheduleFragment.date_name;
            this.item_count = SubScheduleFragment.schedule_count;
            this.img_id = date_img;
            this.mContext = context;
        }

        @Override
        public customAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new viewHolder(inflater.inflate(R.layout.date_item, parent, false));
        }

        @Override
        public void onBindViewHolder(customAdapter.viewHolder holder, int position) {
            holder.mName.setText(item_name[position]);
            holder.mImage.setImageResource(img_id[position]);
            holder.mCount.setText(item_count[position]);
            holder.mDetail.setText("detail" + position);
        }

        @Override
        public int getItemCount() {
            return SubScheduleFragment.date_name == null ? 0 : SubScheduleFragment.date_name.length;
        }

        public class viewHolder extends RecyclerView.ViewHolder {
            public TextView mName;
            public ImageView mImage;
            public TextView mCount;
            public TextView mDetail;

            public viewHolder(View itemView) {
                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.date_name);
                mImage = (ImageView) itemView.findViewById(R.id.date_img);
                mCount = (TextView) itemView.findViewById(R.id.schedule_count);
                mDetail = (TextView) itemView.findViewById(R.id.date_detail);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "position: " + item_name[getAdapterPosition()]);
                        Toast.makeText(getActivity(), "click: " + item_name[getAdapterPosition()], Toast.LENGTH_SHORT).show();
                        HomeFragment.viewPager.setCurrentItem(1);
                    }
                });
            }
        }
    }
}

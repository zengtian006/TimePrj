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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Zeng on 2015/12/16.
 */
public class SubScheduleFragment extends Fragment {
    private final static String TAG = SubScheduleFragment.class.getSimpleName();
    RecyclerView rListView;
    CustomAdapter customAdapter;
    List<Integer> date_img;
    List<String> date_name;
    List<Integer> schedule_count;

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
        customAdapter = new CustomAdapter(getActivity());
        rListView.setAdapter(customAdapter);
//        customAdapter.addItem();
    }

    private void findView(View rootView) {
        rListView = (RecyclerView) rootView.findViewById(R.id.date_list);
    }

    private void loadSchedule() {
        schedule_count = new ArrayList<Integer>();
        date_name = new ArrayList<String>();
        date_img = new ArrayList<Integer>();
        for (int i = 0; i < 7; i++) {
            schedule_count.add(i);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, i);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            if (i == 0) {
                formattedDate += " (today)";
            }
            date_name.add(formattedDate);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE");
            String dayofWeek = sdf.format(c.getTime());
            if (dayofWeek.equals("Mon")) {
                date_img.add(R.drawable.monday);
            } else if (dayofWeek.equals("Tue")) {
                date_img.add(R.drawable.tuesday);
            } else if (dayofWeek.equals("Wed")) {
                date_img.add(R.drawable.wednesday);
            } else if (dayofWeek.equals("Thu")) {
                date_img.add(R.drawable.thursday);
            } else if (dayofWeek.equals("Fri")) {
                date_img.add(R.drawable.friday);
            } else if (dayofWeek.equals("Sat")) {
                date_img.add(R.drawable.saturday);
            } else if (dayofWeek.equals("Sun")) {
                date_img.add(R.drawable.sunday);
            }
            Log.v(TAG, "Formate time => " + formattedDate);
            Log.v(TAG, "date of week => " + dayofWeek);
        }
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

        private final LayoutInflater inflater;
        private final Context mContext;
        private final List<String> item_name;
        private final List<Integer> item_count;
        private final List<Integer> img_id;

        public CustomAdapter(Context context) {
            // TODO Auto-generated constructor stub
            inflater = LayoutInflater.from(context);
            this.item_name = SubScheduleFragment.this.date_name;
            this.item_count = SubScheduleFragment.this.schedule_count;
            this.img_id = SubScheduleFragment.this.date_img;
            this.mContext = context;
        }

        @Override
        public CustomAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new viewHolder(inflater.inflate(R.layout.date_item, parent, false));
        }

        public void addItem() {
            this.item_name.add("test");
            this.item_count.add(99);
            this.img_id.add(R.drawable.monday);
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(CustomAdapter.viewHolder holder, int position) {
            holder.mName.setText(item_name.get(position));
            holder.mImage.setImageResource(img_id.get(position));
            holder.mCount.setText(item_count.get(position).toString());
            holder.mDetail.setText("detail" + position);
        }

        @Override
        public int getItemCount() {
            return SubScheduleFragment.this.date_name == null ? 0 : SubScheduleFragment.this.date_name.size();
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
                        Log.v(TAG, "position: " + item_name.get(getAdapterPosition()));
                        Toast.makeText(getActivity(), "click: " + item_name.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                        HomeFragment.viewPager.setCurrentItem(1);
                    }
                });
            }
        }
    }

}

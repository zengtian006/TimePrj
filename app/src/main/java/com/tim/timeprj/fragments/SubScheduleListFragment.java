package com.tim.timeprj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
public class SubScheduleListFragment extends Fragment {
    private final static String TAG = SubScheduleListFragment.class.getSimpleName();

    RecyclerView rScheduleListView;
    CustomAdapter customAdapter;
    FloatingActionButton fab;

    List<String> event_item_name;
    List<String> event_time;
    List<Integer> event_img;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule_list, container, false);
        findView(rootView);
        setListener();
        setView();
        return rootView;
    }

    private void setListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAdapter.addItem();
            }
        });
    }

    private void setView() {
        loadSchedule();
        rScheduleListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        customAdapter = new CustomAdapter(getActivity());
        rScheduleListView.setAdapter(customAdapter);
    }

    private void findView(View rootView) {
        rScheduleListView = (RecyclerView) rootView.findViewById(R.id.schedule_list);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
    }

    private void loadSchedule() {
        event_item_name = new ArrayList<String>();
        event_time = new ArrayList<String>();
        event_img = new ArrayList<Integer>();
        for (int i = 0; i < 2; i++) {
            event_item_name.add(String.valueOf(i));
            event_time.add(String.valueOf(i));
            event_img.add(R.drawable.monday);
        }
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

        private final LayoutInflater inflater;
        private final Context mContext;
        private final List<String> event_time;
        private final List<String> event_item_name;
        private final List<Integer> img_id;

        public CustomAdapter(Context context) {
            // TODO Auto-generated constructor stub
            inflater = LayoutInflater.from(context);
            this.event_time = SubScheduleListFragment.this.event_time;
            this.event_item_name = SubScheduleListFragment.this.event_item_name;
            this.img_id = SubScheduleListFragment.this.event_img;
            this.mContext = context;
        }

        @Override
        public CustomAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new viewHolder(inflater.inflate(R.layout.schedule_item, parent, false));
        }

        public void addItem() {
            this.event_time.add("test");
            this.event_item_name.add("haha");
            this.img_id.add(R.drawable.monday);
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(CustomAdapter.viewHolder holder, int position) {
            holder.mTime.setText(event_time.get(position));
            holder.mImage.setImageResource(img_id.get(position));
            holder.mName.setText(event_item_name.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return SubScheduleListFragment.this.event_time == null ? 0 : SubScheduleListFragment.this.event_time.size();
        }


        public class viewHolder extends RecyclerView.ViewHolder {
            public TextView mTime;
            public ImageView mImage;
            public TextView mName;

            public viewHolder(View itemView) {
                super(itemView);
                mTime = (TextView) itemView.findViewById(R.id.event_time);
                mName = (TextView) itemView.findViewById(R.id.event_item_name);
                mImage = (ImageView) itemView.findViewById(R.id.event_img);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "position: " + event_time.get(getAdapterPosition()));
                        Toast.makeText(getActivity(), "click: " + event_time.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                        HomeFragment.viewPager.setCurrentItem(1);
                    }
                });
            }
        }
    }
}

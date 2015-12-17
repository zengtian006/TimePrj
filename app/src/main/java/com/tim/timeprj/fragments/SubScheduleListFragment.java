package com.tim.timeprj.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tim.timeprj.R;
import com.tim.timeprj.activity.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import swipemenulistview.BaseSwipListAdapter;
import swipemenulistview.SwipeMenu;
import swipemenulistview.SwipeMenuCreator;
import swipemenulistview.SwipeMenuItem;
import swipemenulistview.SwipeMenuListView;

/**
 * Created by Zeng on 2015/12/16.
 */
public class SubScheduleListFragment extends Fragment {
    private final static String TAG = SubScheduleListFragment.class.getSimpleName();

    private SwipeMenuListView mListView;
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
//                customAdapter.addItem();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    private void setView() {
        loadSchedule();
        customAdapter = new CustomAdapter(getActivity());
//        rScheduleListView.setAdapter(customAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                deleteItem.setBackground(R.color.colorAccent);
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Log.v(TAG, "delete item");
                        break;
                }
                return false;
            }
        });
        mListView.setMenuCreator(creator);
        mListView.setAdapter(customAdapter);
        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    private void findView(View rootView) {
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mListView = (SwipeMenuListView) rootView.findViewById(R.id.listView);

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

    class CustomAdapter extends BaseSwipListAdapter {

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
        public int getCount() {
            return SubScheduleListFragment.this.event_time == null ? 0 : SubScheduleListFragment.this.event_time.size();
        }

        public void addItem() {
            this.event_time.add("test");
            this.event_item_name.add("haha");
            this.img_id.add(R.drawable.monday);
            notifyDataSetChanged();
        }

        @Override
        public List getItem(int position) {
            List resultList = new ArrayList();
            resultList.add(event_item_name.get(position));
            resultList.add(event_time.get(position));
            return resultList;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity().getApplicationContext(),
                        R.layout.schedule_item, null);
                new ViewHolder(convertView);
            }

            ViewHolder holder = (ViewHolder) convertView.getTag();

            holder.mTime.setText(event_time.get(position));
            holder.mImage.setImageResource(img_id.get(position));
            holder.mName.setText(event_item_name.get(position));
            return convertView;
        }


        class ViewHolder {
            public TextView mTime;
            public ImageView mImage;
            public TextView mName;

            public ViewHolder(View itemView) {
                mTime = (TextView) itemView.findViewById(R.id.event_time);
                mName = (TextView) itemView.findViewById(R.id.event_item_name);
                mImage = (ImageView) itemView.findViewById(R.id.event_img);
                itemView.setTag(this);
            }
        }

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}

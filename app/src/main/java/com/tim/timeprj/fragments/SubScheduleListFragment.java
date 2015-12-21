package com.tim.timeprj.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tim.timeprj.R;
import com.tim.timeprj.activity.AddScheduleActivity;

import java.util.ArrayList;
import java.util.List;

import swipemenulistview.BaseSwipListAdapter;
import swipemenulistview.SwipeMenu;
import swipemenulistview.SwipeMenuCreator;
import swipemenulistview.SwipeMenuItem;
import swipemenulistview.SwipeMenuListView;

/**
 * Created by Zeng on 2015/12/16.
 */
public class SubScheduleListFragment extends Fragment implements OnChartValueSelectedListener {
    private final static String TAG = SubScheduleListFragment.class.getSimpleName();

    private SwipeMenuListView mListView;
    static CustomAdapter customAdapter;
    FloatingActionButton fab;

    private PieChart mChart;
    private Typeface tf;
    protected String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    ArrayList<Integer> colors;

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
                getParentFragment().startActivityForResult(new Intent(getActivity(), AddScheduleActivity.class), 10);
            }
        });
        mChart.setOnChartValueSelectedListener(this);
    }

    private void setView() {
        //set pie chart
        mChart.setDescription("Occupation of Time");
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf"));
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);
        mChart.setDrawCenterText(true);
        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        mChart.setUsePercentValues(false);
        setData(3, 24);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        // set pie chart end

        //
        loadSchedule();
        customAdapter = new CustomAdapter(getActivity());
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
        mChart = (PieChart) rootView.findViewById(R.id.chart1);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mListView = (SwipeMenuListView) rootView.findViewById(R.id.listView);
    }

    private void setData(int count, float range) {
        float mult = range;
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        yVals1.add(new Entry(2, 0));
        yVals1.add(new Entry(6, 1));
        yVals1.add(new Entry(4, 2));
        yVals1.add(new Entry(12, 3));
        for (int i = 0; i < count + 1; i++) {
//            yVals1.add(new Entry((float) (i * mult) + mult / 5, i));
            Log.v(TAG, yVals1.get(i).toString());
        }


        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count + 1; i++)
            xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(5f);
        // add a lot of colors
        colors = new ArrayList<Integer>();

//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);

//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        //IMPORTANT
        data.setValueFormatter(new DefaultValueFormatter(1));
        //
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(tf);
        mChart.setData(data);
        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("6 / 24");
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 0, s.length(), 0);
        s.setSpan(new RelativeSizeSpan(2f), 0, s.length(), 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);

//        SpannableString s = new SpannableString("MPAndroidChart developed by Philipp Jahoda");
//        s.setSpan(new RelativeSizeSpan(1f), 0, 14, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
//        s.setSpan(new RelativeSizeSpan(.5f), 14, s.length() - 15, 0);
//        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    private void loadSchedule() {
        event_item_name = new ArrayList<String>();
        event_time = new ArrayList<String>();
        event_img = new ArrayList<Integer>();
//        List<Integer> colorList = new ArrayList<Integer>();
//        colorList.add(ContextCompat.getColor(getActivity(), R.color.button_text_color));
//        colorList.add(ContextCompat.getColor(getActivity(), R.color.colorAccent));
//        colorList.add(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        for (int i = 0; i < 2; i++) {
            event_item_name.add(String.valueOf(i));
            event_time.add(String.valueOf(i));
            event_img.add(colors.get(i));
        }
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
        Log.v(TAG, "Value: " + e.getVal() + ", xIndex: " + e.getXIndex() + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {

    }

    class CustomAdapter extends BaseSwipListAdapter {

        private final LayoutInflater inflater;
        private final Context mContext;
        private final List<String> event_time;
        private final List<String> event_item_name;
        private final List<Integer> event_color_id;

        public CustomAdapter(Context context) {
            // TODO Auto-generated constructor stub
            inflater = LayoutInflater.from(context);
            this.event_time = SubScheduleListFragment.this.event_time;
            this.event_item_name = SubScheduleListFragment.this.event_item_name;
            this.event_color_id = SubScheduleListFragment.this.event_img;
            this.mContext = context;
        }


        @Override
        public int getCount() {
            return SubScheduleListFragment.this.event_time == null ? 0 : SubScheduleListFragment.this.event_time.size();
        }

        public void addItem(String test) {
            this.event_time.add(test);
            this.event_item_name.add("haha");
            this.event_color_id.add(ContextCompat.getColor(getActivity(), R.color.colorAccent));
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

            Drawable d = ContextCompat.getDrawable(getActivity(), R.drawable.tag_schedule);
            holder.mImage.setImageDrawable(d);
            holder.mImage.setBackgroundColor(event_color_id.get(position));
            holder.mName.setText(event_item_name.get(position));
            holder.mTime.setText(event_time.get(position));
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

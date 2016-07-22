package com.knunu.android.whesiknow;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindActivity extends AppCompatActivity {
    PopupWindow pop1;

    DatePicker picker;
    @BindView(R.id.find_toolbar) Toolbar toolbar;
    @BindView(R.id.search_view) SearchView searchView;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    TextView timeLabel;
    Calendar dateAndtime = Calendar.getInstance();
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndtime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndtime.set(Calendar.MINUTE, minute);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);

        toolbar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setSearchView(searchView);
        setRecyclerView(recyclerView);

        TextView tt = (TextView) findViewById(R.id.textView12);
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(FindActivity.this, t, dateAndtime.get(Calendar.HOUR_OF_DAY), dateAndtime.get(Calendar.MINUTE),true).show();
            }
        });
    }

    public void calendar(View v) {
        View convertView = getLayoutInflater().inflate(R.layout.activity_popup, null);
        pop1 = new PopupWindow(convertView, LayoutParams.WRAP_CONTENT, 100);
        pop1.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String text = null;

        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                return false;
        }

        return true;
    }

    private void setSearchView(SearchView searchView) {
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(id);
        searchEditText.setTextColor(Color.WHITE);
        searchEditText.setHintTextColor(Color.WHITE);
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "지역"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "서울"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "경기"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "대전"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "대구"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "부산"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "울산"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "음식 종류"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "한식"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "중식"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "양식"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "일식"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "기타"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "기타"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "기타"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "기타"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "기타"));

        recyclerView.setAdapter(new ExpandableListAdapter(data));
    }
}
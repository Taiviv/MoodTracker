package com.chartier.virginie.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chartier.virginie.moodtracker.R;
import com.chartier.virginie.moodtracker.model.Mood;
import com.chartier.virginie.moodtracker.model.MoodData;
import com.chartier.virginie.moodtracker.view.MoodAdapter;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements MoodAdapter.OnItemClickListener {

    // FOR DATA
    private MoodAdapter mAdapter;
    private List<Mood> mMoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // First the activity load the data
        this.mMoods = MoodData.weekListMood(this);
        // Then we build the layout with the data stored before
        buildRecyclerView();
    }

    // This method make a toast custom
    private void toastMaker(String comment) {

        // Second inflate the layout from XML and set text
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.toast_custom_container));
        TextView toastText = layout.findViewById(R.id.text);
        toastText.setText(comment);

        // Finally creation of the toast and set some properties
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    /* This method handle the construction of an adaptive layout
     * Two classes are called whenever this methods is used
     * */
    private void buildRecyclerView() {
        mAdapter = new MoodAdapter(mMoods, this, this);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(Mood moodClicked) {
        if (moodClicked.getmComment() != null && !moodClicked.getmComment().isEmpty()) {
            toastMaker(moodClicked.getmComment());
        }
    }
}

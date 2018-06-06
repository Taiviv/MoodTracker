package com.chartier.virginie.moodtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    // FOR DESIGN
    private RecyclerView mRecyclerView; //switch to true if you want to enable the developer/debug mode
    private RecyclerView.LayoutManager mLayoutManager;

    // FOR DATA
    private MoodAdapter mAdapter;
    private ArrayList<Mood> mMoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //First the activity load the data
        this.mMoods = MoodData.loadData(this);
        //Then we build the layout with the data stored before
        buildRecyclerView();
    }



    //This method make a toast custom
    public void toastMaker(int position) {
        //First, get the comment store in the variable
        String text = mMoods.get(position).getComment();

        //Second inflate the layout from XML and set text
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom,
                (ViewGroup) findViewById(R.id.toast_custom_container));
        TextView toastText = layout.findViewById(R.id.text);
        toastText.setText(text);

        //Finally creation of the toast and set some properties
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        //Notify the adapter of recycler view of changing
        mAdapter.notifyItemChanged(position);
    }


    /* This method handle the construction of an adaptive layout
     * Two classes are called whenever this methods is used
     * */
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //Call new classes
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MoodAdapter(mMoods, this);

        //Set them with natives methods
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Here we call a new class with his interface in order to handle the click on each item (meaning the layout)
        mAdapter.setOnItemClickListener(new MoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Here we allow the toast text to appear only if the comment is not empty at his own position
                if (!mMoods.get(position).getComment().equals(""))
                    toastMaker(position);
            }
        });
    }
}

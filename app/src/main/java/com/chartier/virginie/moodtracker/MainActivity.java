package com.chartier.virginie.moodtracker;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageButton mComment;
    private ImageButton mHistory;
    private ImageView mSmiley;
    private ConstraintLayout mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureWidgets();
    }


    // Declare all components
    private void configureWidgets() {
        mComment = findViewById(R.id.main_activity_comment);
        mHistory = findViewById(R.id.main_activity_history);
        mSmiley = findViewById(R.id.main_activity_img);
        mBackground = findViewById(R.id.main_activity_background);
    }
}

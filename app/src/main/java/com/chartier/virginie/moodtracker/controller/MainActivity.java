package com.chartier.virginie.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.chartier.virginie.moodtracker.R;

import com.chartier.virginie.moodtracker.model.MoodData;
import com.chartier.virginie.moodtracker.view.OnSwipeTouchListener;

import static com.chartier.virginie.moodtracker.utils.Constants.BUNDLE_REQUEST_CODE;
import static com.chartier.virginie.moodtracker.utils.Constants.LIST_COLOR_IMG;

public class MainActivity extends AppCompatActivity {

    // FOR DESIGN
    private ImageButton mComment;
    private ImageButton mHistory;
    private ImageView mSmiley;
    private ConstraintLayout mBackground;
    private ImageView mArrowUp;
    private ImageView mArrowDown;
    private View swipeView;

    // FOR DATA
    private int indexMood = 3;
    private AlphaAnimation anim;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureWidgets();
        this.configureCommentButtonOnClick();
        this.configureHistoryButtonOnClick();
        this.configureSwipeView();
        this.startAnimation();
    }


    // Declare all components
    private void configureWidgets() {
        mComment = findViewById(R.id.main_activity_comment);
        mHistory = findViewById(R.id.main_activity_history);
        mSmiley = findViewById(R.id.main_activity_img);
        mBackground = findViewById(R.id.main_activity_background);
        mArrowDown = findViewById(R.id.main_activity_arrow_down);
        mArrowUp = findViewById(R.id.main_activity_arrow_up);
        swipeView = findViewById(R.id.main_activity_view);
    }


    /* Button on the left bottom which open an AlertDialog on click
     * with an alert dialog with text input and two buttons 'Valider'
     * and 'Annuler'
     * The user can write a comment
     */
    private void configureCommentButtonOnClick() {
        mComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        final EditText edittext = new EditText(MainActivity.this);

        builder.setMessage("Commentaire")
                .setView(edittext)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //registration of comment if the user writes one
                        MoodData.createAndSaveMood(indexMood, edittext.getText().toString(), MainActivity.this);
                        Log.e("Tag", "le contenu de mon commentaire est : " + edittext.getText().toString());
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create()
                .show();
    }


    /* Button to the right bottom of the screen
     * when user click on it, the activity HistoryActivity.java is launched
     */
    private void configureHistoryButtonOnClick(){
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivity = new Intent(MainActivity.this, HistoryActivity.class);
                startActivityForResult(historyActivity, BUNDLE_REQUEST_CODE);
            }
        });
    }

    // This method configure the swipe
    private void configureSwipeView(){
        swipeView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                updateDesign(false);
                MoodData.createAndSaveMood(indexMood, null, MainActivity.this);
            }

            @Override
            public void onSwipeUp() {
                updateDesign(true);
                MoodData.createAndSaveMood(indexMood, null, MainActivity.this);
            }

        });
    }


    // This method configure the update design
    private void updateDesign(boolean isUp){
        Integer maxIndex = isUp ? 0 : 4;

        if(isUp){
            indexMood++;
            getSound(R.raw.sound_happy);
            if(indexMood >= maxIndex)showArrow(mArrowDown , maxIndex , mArrowUp);
        }
        else{
            indexMood--;
            getSound(R.raw.sound_unhappy);
            if(indexMood <= maxIndex)showArrow(mArrowUp , maxIndex , mArrowDown);

        }

        mSmiley.setImageResource(LIST_COLOR_IMG[1][indexMood]);
        mBackground.setBackgroundColor(getResources().getColor(LIST_COLOR_IMG[0][indexMood]));
    }


    // This method manage animation of arrows
    private void showArrow(ImageView arrow, int maxIndex, ImageView oppositeArrow){
        arrow.clearAnimation();
        oppositeArrow.clearAnimation();
        arrow.setVisibility(View.GONE);
        oppositeArrow.setVisibility(View.GONE);
        if (indexMood == maxIndex){
            arrow.setVisibility(View.VISIBLE);
            arrow.startAnimation(anim);
        }
    }


    //Allow to add sound or music from raw folder in app by using MediaPlayer
    private void getSound(int sound) {
        mp = MediaPlayer.create(this, sound);
        mp.start();
    }

    //This method configure AlphaAnimation
    private void startAnimation(){
        this.anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); // Blink management with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
    }
}

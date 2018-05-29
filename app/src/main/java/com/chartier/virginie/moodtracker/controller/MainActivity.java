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
import android.widget.Toast;
import com.chartier.virginie.moodtracker.R;
import com.chartier.virginie.moodtracker.view.OnSwipeTouchListener;


public class MainActivity extends AppCompatActivity {

    private ImageButton mComment;
    private ImageButton mHistory;
    private ImageView mSmiley;
    private ConstraintLayout mBackground;
    private ImageView mArrowUp;
    private ImageView mArrowDown;
    private String comment;
    private View swipeView;
    private int[][] LIST_COLOR_IMG = {
            {R.color.faded_red,
                    R.color.warm_grey,
                    R.color.cornflower_blue_65,
                    R.color.light_sage,
                    R.color.banana_yellow},
            {R.drawable.smiley_sad,
                    R.drawable.smiley_disappointed,
                    R.drawable.smiley_normal,
                    R.drawable.smiley_happy,
                    R.drawable.smiley_super_happy}};
    private int indexMood = 3;
    private AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureWidgets();
        this.configureCommentButtonOnClick();
        this.configureHistoryButtonOnClick();
        this.configureSwipeView();
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
                        comment = edittext.getText().toString();
                        Log.e("Tag", "le contenu de mon commentaire est : " + comment);
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
                Intent mIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(mIntent);
            }
        });
    }


    private void configureSwipeView(){
        swipeView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                indexMood--;
                mSmiley.setImageResource(LIST_COLOR_IMG[1][indexMood]);
                mBackground.setBackgroundColor(getResources().getColor(LIST_COLOR_IMG[0][indexMood]));
                mArrowDown.setVisibility(View.GONE);
                mArrowUp.setVisibility(View.GONE);
                if (indexMood < 4){
                    Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
                    getSound(R.raw.sound_unhappy);
                    mArrowDown.clearAnimation();
                    mArrowUp.clearAnimation();
                    if (indexMood==0){
                        mArrowUp.setVisibility(View.GONE);
                        anim.setDuration(500); // Blink management with this parameter
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(Animation.INFINITE);
                        mArrowDown.startAnimation(anim);
                    }
                }
            }

            @Override
            public void onSwipeUp() {
                indexMood++;
                mSmiley.setImageResource(LIST_COLOR_IMG[1][indexMood]);
                mBackground.setBackgroundColor(getResources().getColor(LIST_COLOR_IMG[0][indexMood]));
                mArrowDown.setVisibility(View.GONE);
                mArrowUp.setVisibility(View.GONE);
                if (indexMood > 0){
                    Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
                    getSound(R.raw.sound_happy);
                    mArrowDown.clearAnimation();
                    mArrowUp.clearAnimation();
                    if (indexMood ==4){
                        mArrowDown.setVisibility(View.GONE);
                        anim.setDuration(500); // Blink management with this parameter
                        anim.setStartOffset(20);
                        anim.setRepeatMode(Animation.REVERSE);
                        anim.setRepeatCount(Animation.INFINITE);
                        mArrowUp.startAnimation(anim);
                    }
                }
            }
        });
    }

    //Allow to add sound or music from raw folder in app by using MediaPlayer
    private void getSound(int sound) {
        mp = MediaPlayer.create(this, sound);
        mp.start();
    }
}

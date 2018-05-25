package com.chartier.virginie.moodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageButton mComment;
    private ImageButton mHistory;
    private ImageView mSmiley;
    private ConstraintLayout mBackground;
    public String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureWidgets();
        this.configureCommentButtonOnClick();
        this.configureHistoryButtonOnClick();
    }


    // Declare all components
    private void configureWidgets() {
        mComment = findViewById(R.id.main_activity_comment);
        mHistory = findViewById(R.id.main_activity_history);
        mSmiley = findViewById(R.id.main_activity_img);
        mBackground = findViewById(R.id.main_activity_background);
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
        });
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
}

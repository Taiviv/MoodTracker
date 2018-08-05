package com.chartier.virginie.moodtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chartier.virginie.moodtracker.R;
import com.chartier.virginie.moodtracker.helpers.DateHelper;
import com.chartier.virginie.moodtracker.model.Mood;
import com.chartier.virginie.moodtracker.model.MoodData;
import com.chartier.virginie.moodtracker.model.MoodPreferences;
import com.chartier.virginie.moodtracker.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.chartier.virginie.moodtracker.utils.Constants.NUMBER_ITEM;
import static com.chartier.virginie.moodtracker.utils.Constants.formatter;


/**
 * Created by Virginie Chartier alias Taiviv on 31/05/2018.
 */

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.ViewHolder> {


    private List<Mood> mMoods;
    private OnItemClickListener mListener;
    private Context mContext;

    public MoodAdapter(List<Mood> moods, Context context, OnItemClickListener listener) {
        mMoods = moods;
        mContext = context;
        mListener = listener;
    }

    @Override
    public MoodAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items, parent, false);
        //Each sub layout height is adapting with the parent (device height) we divide the screen by seven (nbr of day in week)
        view.getLayoutParams().height = parent.getHeight() / 7;

        return new MoodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodAdapter.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.itemView.setBackgroundResource(Constants.LIST_COLOR_IMG[0][mMoods.get(position).getMoodId()]);
        holder.mTextView.setText(getDateText(mMoods.get(position)));
        holder.mImageViewMood.setImageResource(Constants.LIST_COLOR_IMG[1][mMoods.get(position).getMoodId()]);
        holder.mImageViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mMoods.get(position));
            }
        });
        // This handles the visibility of the comment icon
        if (mMoods.get(position).getmComment() == null)
            holder.mImageViewComment.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        //This allow to keep only 7 items on the screen and then remove the older one when limit is reached
        if (mMoods.size() == NUMBER_ITEM)
            mMoods.remove(0);
        return mMoods.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnItemClickListener {
        void onItemClick(Mood moodClicked);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageViewMood;
        public ImageView mImageViewComment;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.history_items_text);
            mImageViewMood = itemView.findViewById(R.id.history_items_img);
            mImageViewComment = itemView.findViewById(R.id.history_items_comment);
        }
    }

    private String getDateText(Mood mood) {
        int nbDaysBeetween = DateHelper.daysBetween(mood.getmDate(), new Date());
        switch (nbDaysBeetween) {
            case 0:
                return mContext.getResources().getString(R.string.history_date_today);
            case 1:
                return mContext.getResources().getString(R.string.history_date_yesterday);
            case 2:
                return mContext.getResources().getString(R.string.history_date_two_days);
            case 3:
                return mContext.getResources().getString(R.string.history_date_three_days);
            case 4:
                return mContext.getResources().getString(R.string.history_date_four_days);
            case 5:
                return mContext.getResources().getString(R.string.history_date_five_days);
            case 6:
                return mContext.getResources().getString(R.string.history_date_six_days);
            case 7:
                return mContext.getResources().getString(R.string.history_date_week);
            default:
                return Constants.formatter.format(mood.getmDate());
        }
    }
}
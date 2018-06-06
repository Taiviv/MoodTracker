package com.chartier.virginie.moodtracker.view;

import android.app.Activity;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chartier.virginie.moodtracker.R;
import com.chartier.virginie.moodtracker.model.Mood;

import java.util.ArrayList;

import static com.chartier.virginie.moodtracker.utils.Constants.NUMBER_ITEM;

/**
 * Created by Virginie Chartier alias Taiviv on 31/05/2018.
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {

    private ArrayList<Mood> mMoods;
    private OnItemClickListener mListener;
    private Context mContext;

    //Constructor used in the build buildRecyclerView method
    public MoodAdapter(ArrayList<Mood> moods, Context context) {
        mMoods = moods;
        mContext = context;
    }


    /* This method handle the click on items parent or/and their child
     * listener param you can call View.OnClickListener()
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    /* Override methods from RecyclerView class
     * These handle the creation, the holding and the size of the item list
     * This is the heart of the Adapter.
     */
    @Override
    public MoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Here we inflate the xml layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items, parent, false);
        //Each sub layout height is adapting with the parent (device height) we divide the screen by seven (nbr of day in week)
        view.getLayoutParams().height = parent.getHeight() / 7;
        //Initialize a new class wih all the data holding
        MoodViewHolder mvh = new MoodViewHolder(view, mListener);
        return mvh;
    }


    /* This method handle the content and the position of each item
     *
     * holder : param to update the RecyclerView.ViewHolder contents with the item at the given position
     * position : param the position of a data item within an Adapter.
     */
    @Override
    public void onBindViewHolder(MoodViewHolder holder, int position) {
        //Define the item position
        final Mood currentItem = mMoods.get(position);

        //Construction of the item element by using MoodViewHolder and Mood constructor and methods
        holder.mRelativeLayout.setBackgroundResource(currentItem.getColor());
        //Here we subtract the array size with the current position, this way we can "switch" the index position
        holder.mTextView.setText(currentItem.getDate((mMoods.size() - 1) - position));
        holder.mImageViewComment.setImageResource(currentItem.getIconComment());
        holder.mToastView.setText(currentItem.getComment());

        //This handle the visibility of comment icon
        if (currentItem.getComment().equals(""))
            holder.mImageViewComment.setVisibility(View.GONE);

        layoutResize(holder, currentItem);
    }


    //This method return the number of item holding
    @Override
    public int getItemCount() {
        //This allow to keep only 7 items on the screen and then remove the older one when limit is reached
        if (mMoods.size() == NUMBER_ITEM)
            mMoods.remove(0);
        return mMoods.size();
    }


    //This method allow to modify the layout size useful to display item at different size range
    public void layoutResize(MoodViewHolder holder, Mood currentItem) {
        //Display metric can catch the width or/and height of the device
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //This variable take the width of the device and with the index value and a calculation, the layout width size is adjusting
        int deviceWidth;
        deviceWidth = (displaymetrics.widthPixels * 20 * (currentItem.getSmileyValue() + 1)) / 100;
        //Here we initiate the new width to each item layout
        holder.mRelativeLayout.getLayoutParams().width = deviceWidth;
    }

    //This interface provide onItemClick method which allow to click on something in the activities class
    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    /* This class work with the adapter
     * It's necessary to hold each elements of the main layout
     * This class allow to call them directly in the adapter for better performance
     */
    public static class MoodViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout mRelativeLayout;
        public TextView mTextView;
        public ImageView mImageViewComment;
        public TextView mToastView;


        private MoodViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            this.configureWidgets();


            /* Here we give the possibility to click on item (itemView)
             * We can choose to click on a specific element like an image view instead
             */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        // Declare all components
        private void configureWidgets() {
            mRelativeLayout = itemView.findViewById(R.id.history_items_layout);
            mTextView = itemView.findViewById(R.id.history_items_text);
            mImageViewComment = itemView.findViewById(R.id.history_items_comment);
            mToastView = itemView.findViewById(R.id.history_items_toast);
        }
    }
}
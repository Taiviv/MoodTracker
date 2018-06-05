package com.chartier.virginie.moodtracker.utils;

import com.chartier.virginie.moodtracker.R;

/**
 * Created by Virginie Chartier alias Taiviv on 28/06/2018.
 */
public abstract class Constants {

    private Constants(){}

    public static final int BUNDLE_REQUEST_CODE = 42;
    public static final int[][] LIST_COLOR_IMG = {
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
}

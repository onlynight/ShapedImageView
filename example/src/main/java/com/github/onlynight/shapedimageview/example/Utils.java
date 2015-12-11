package com.github.onlynight.shapedimageview.example;

import android.content.res.Resources;

/**
 * Created by lion on 2015/12/11 0011.
 */
public class Utils {

    //dp to px
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}

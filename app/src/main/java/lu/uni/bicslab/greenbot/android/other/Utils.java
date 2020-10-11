package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;

import lu.uni.bicslab.greenbot.android.R;

public class Utils {

    public static int getStatusBarHeight(Context context) {
        int height = (int) context.getResources().getDimension(R.dimen.statusbar_size);
        return height;
    }

}

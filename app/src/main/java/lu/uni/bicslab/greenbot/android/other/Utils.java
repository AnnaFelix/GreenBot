package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

import lu.uni.bicslab.greenbot.android.R;

public class Utils {
    public  static String id = "";

    public static int getStatusBarHeight(Context context) {
        int height = (int) context.getResources().getDimension(R.dimen.statusbar_size);
        return height;
    }

    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
    public static Drawable GetImage(Context c, String ImageName) {
        try {
            return c.getResources().getDrawable(c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName()));
        }catch (Exception e){
            return c.getResources().getDrawable(c.getResources().getIdentifier("ind_animal", "drawable", c.getPackageName()));

        }
    }

}

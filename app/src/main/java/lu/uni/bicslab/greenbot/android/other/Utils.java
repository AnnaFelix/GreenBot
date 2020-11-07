package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import lu.uni.bicslab.greenbot.android.R;

public class Utils {

    public  static String id = "";
    public static final String PREF_NAME = "GREENBOT_PREFERENCES";
    public static final int MODE = Context.MODE_PRIVATE;


    public static final String name = "name";
    public static final String shrprofilename="profile";

    public static final int user_underconsideration = 1;
    public static final int user_notloggedin = 0;
    public static final int user_loggedin = 2;

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
        } catch (Exception e) {
            return c.getResources().getDrawable(c.getResources().getIdentifier("ind_animal", "drawable", c.getPackageName()));

        }

    }

        public static void writeBoolean(Context context, String key, boolean value) {
            getEditor(context).putBoolean(key, value).commit();
        }

        public static boolean readBoolean(Context context, String key,
        boolean defValue) {
            return getPreferences(context).getBoolean(key, defValue);
        }

        public static void writeInteger(Context context, String key, int value) {
            getEditor(context).putInt(key, value).commit();

        }

        public static int readInteger(Context context, String key, int defValue) {
            return getPreferences(context).getInt(key, defValue);
        }

        public static SharedPreferences getPreferences(Context context) {
            return context.getSharedPreferences(PREF_NAME, MODE);
        }

        public static SharedPreferences.Editor getEditor(Context context) {
            return getPreferences(context).edit();
        }
        public static void saveProfile(Context context, Profile  mProfile ){

            SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = pref.edit();
            Gson gson = new Gson();
            String json = gson.toJson(mProfile);
            prefsEditor.putString(shrprofilename, json);
            prefsEditor.commit();
        }
        public  static Profile readProfileData(Context context){
            Gson gson = new Gson();
            SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            String json = pref.getString(shrprofilename, "");
            try {
                Profile obj = gson.fromJson(json, Profile.class);
                return obj;
            }catch (Exception e){
                return null;

            }
        }

}

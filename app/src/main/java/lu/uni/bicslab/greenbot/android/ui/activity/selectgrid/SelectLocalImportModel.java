package lu.uni.bicslab.greenbot.android.ui.activity.selectgrid;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import lu.uni.bicslab.greenbot.android.R;

public class SelectLocalImportModel {

    String title;
    Drawable image;
    boolean selected;


    public SelectLocalImportModel(String title, Drawable image, boolean selected) {
        this.title = title;
        this.image = image;
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public  static  String[] getTitle(Context mcontext){
        String[] title_Array = new String[]{mcontext.getResources().getString(R.string.conimporte), mcontext.getResources().getString(R.string.conlocal), mcontext.getResources().getString(R.string.bioimporte), mcontext.getResources().getString(R.string.biolocal)};
        return title_Array;
    }
    public  static  Drawable[] getimage(Context mcontext){
       Drawable[] title_Array = new Drawable[]{ContextCompat.getDrawable(mcontext, R.drawable.conimport),
               ContextCompat.getDrawable(mcontext, R.drawable.conloasl), ContextCompat.getDrawable(mcontext, R.drawable.bioimport), ContextCompat.getDrawable(mcontext, R.drawable.bioimport)};
        return title_Array;
    }

    public  static  String[] getTitle2(Context mcontext){
        String[] title_Array = new String[]{mcontext.getResources().getString(R.string.Soutenir),
                mcontext.getResources().getString(R.string.Connaitre), mcontext.getResources().getString(R.string.Encourager),
                mcontext.getResources().getString(R.string.privilege)};
        return title_Array;
    }
    public  static  Drawable[] getimage2(Context mcontext){
        Drawable[] title_Array = new Drawable[]{ContextCompat.getDrawable(mcontext, R.drawable.socio_economique_icon),
                ContextCompat.getDrawable(mcontext, R.drawable.socio_ecologique_icon),
                ContextCompat.getDrawable(mcontext, R.drawable.socio_culturel_icon), ContextCompat.getDrawable(mcontext, R.drawable.socio_politique_icon)};
        return title_Array;
    }
}

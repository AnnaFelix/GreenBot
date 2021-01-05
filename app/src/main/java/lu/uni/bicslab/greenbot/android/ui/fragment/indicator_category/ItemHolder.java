package lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import lu.uni.bicslab.greenbot.android.R;

public class ItemHolder extends RecyclerView.ViewHolder {

    public TextView txtName, txtDoc;
    public CardView card_view;
    public ImageView imageview_icon;

    public ItemHolder(View view) {
        super(view);

        txtName = (TextView) view.findViewById(R.id.txtName);
        txtDoc = (TextView) view.findViewById(R.id.txtDoc);
        card_view = (CardView) view.findViewById(R.id.card_view);
        imageview_icon = (ImageView) view.findViewById(R.id.imageview_icon);
    }
}
package lu.uni.bicslab.greenbot.android.ui.indicator;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import lu.uni.bicslab.greenbot.android.R;

public class ItemHolder extends RecyclerView.ViewHolder {

    public TextView txtName, txtDoc;
    CardView card_view;

    public ItemHolder(View view) {
        super(view);
        txtName = (TextView) view.findViewById(R.id.txtName);
        txtDoc = (TextView) view.findViewById(R.id.txtDoc);
        card_view = (CardView) view.findViewById(R.id.card_view);
    }
}
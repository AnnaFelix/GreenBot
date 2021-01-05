package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.activity.selectgrid.SelectLocalImportModel;

public class CustomGridAdapter extends RecyclerView.Adapter<CustomGridAdapter.MyViewHolder> {

    private ArrayList<SelectLocalImportModel> dataModel;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text_title;
        ImageView imageview_icon;
        CardView cardview;

        public MyViewHolder(View view) {
            super(view);
            this.text_title = (TextView) view.findViewById(R.id.text_title);
            this.imageview_icon = (ImageView) view.findViewById(R.id.imageview_icon);
            this.cardview = (CardView) view.findViewById(R.id.card_view);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public CustomGridAdapter(Context mContext, ArrayList<SelectLocalImportModel> data) {
        this.dataModel = data;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_row, parent, false);

        // view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        TextView text_title = holder.text_title;
        ImageView imageview_icon = holder.imageview_icon;
        CardView cardview = holder.cardview;

        text_title.setText(dataModel.get(position).getTitle());
        imageview_icon.setBackground(dataModel.get(position).getImage());
        if (dataModel.get(position).isSelected()) {
            if (dataModel.get(position).getTitle().startsWith("Soute")) {
                cardview.setBackgroundColor(mContext.getResources().getColor(R.color.lightyellow));
            } else if (dataModel.get(position).getTitle().startsWith("Conn")) {
                cardview.setBackgroundColor(mContext.getResources().getColor(R.color.lightgreen));

            } else if (dataModel.get(position).getTitle().startsWith("Encour")) {
                cardview.setBackgroundColor(mContext.getResources().getColor(R.color.lightorange));

            } else if (dataModel.get(position).getTitle().startsWith("Privi")) {
                cardview.setBackgroundColor(mContext.getResources().getColor(R.color.lightblue));

            } else if (dataModel.get(position).getTitle().startsWith("Conve")) {
                cardview.setBackgroundColor(mContext.getResources().getColor(R.color.blue_));
            } else {
                cardview.setBackgroundColor(mContext.getResources().getColor(R.color.lightgreen));
            }
        } else {
            cardview.setBackgroundColor(Color.WHITE);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataModel.get(position).setSelected(!dataModel.get(position).isSelected());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }


}
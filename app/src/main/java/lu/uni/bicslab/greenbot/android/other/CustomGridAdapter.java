package lu.uni.bicslab.greenbot.android.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.activity.selectgrid.SelectLocalImportModel;

public class CustomGridAdapter extends RecyclerView.Adapter<CustomGridAdapter.MyViewHolder> {

    private ArrayList<SelectLocalImportModel> dataModel;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_title;
        ImageView imageview_icon;

        public MyViewHolder(View view) {
            super(view);
            this.text_title = (TextView) view.findViewById(R.id.text_title);
            this.imageview_icon = (ImageView) view.findViewById(R.id.imageview_icon);
        }
    }

    public CustomGridAdapter(ArrayList<SelectLocalImportModel> data) {
        this.dataModel = data;
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

        text_title.setText(dataModel.get(position).getTitle());
        imageview_icon.setBackground(dataModel.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }
}
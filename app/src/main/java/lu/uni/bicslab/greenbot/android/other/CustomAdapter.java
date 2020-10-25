package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.indicator_category.IndicatorCategoryModel;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomView> {

    private List<IndicatorModel> indicatorModel;
    private Context context;

    public CustomAdapter(Context context, List<IndicatorModel>  indicatorModel) {
        this.context = context;
        this.indicatorModel = indicatorModel;

    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new CustomAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        IndicatorModel dessert = indicatorModel.get(position);

        holder.mName.setText(dessert.getName());
        holder.mDescription.setText(dessert.getGeneral_description());
        holder.mFirstLetter.setText(String.valueOf(dessert.getIcon_name()));

    }

    @Override
    public int getItemCount() {
        return indicatorModel == null ? 0 : indicatorModel.size();
    }

    public static class CustomView extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mFirstLetter;

        public CustomView(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.txt_name);
            mDescription = itemView.findViewById(R.id.txt_desc);
            mFirstLetter = itemView.findViewById(R.id.txt_firstletter);
        }
    }
}

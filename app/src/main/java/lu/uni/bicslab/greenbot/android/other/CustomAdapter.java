package lu.uni.bicslab.greenbot.android.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.indicator.IndicatorViewModel;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DessertVh> {

    private List<IndicatorViewModel> desserts;
    private Context context;
    int pos;

    public CustomAdapter(Context context, int pos) {
        this.context = context;
        this.pos = pos;

        desserts = IndicatorViewModel.prepareDesserts(
                context.getResources().getStringArray(R.array.dessert_names),
                context.getResources().getStringArray(R.array.dessert_descriptions));
    }

    @Override
    public DessertVh onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_row, parent, false);
        return new CustomAdapter.DessertVh(view);
    }

    @Override
    public void onBindViewHolder(DessertVh holder, int position) {
        IndicatorViewModel dessert = desserts.get(position);

        holder.mName.setText(dessert.getIndicator_name());
        holder.mDescription.setText(dessert.getIndicator_data());
        holder.mFirstLetter.setText(String.valueOf(dessert.getIndicator_image()));

    }

    @Override
    public int getItemCount() {
        return desserts == null ? 0 : desserts.size();
    }

    public static class DessertVh extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;
        private TextView mFirstLetter;

        public DessertVh(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.txt_name);
            mDescription = itemView.findViewById(R.id.txt_desc);
            mFirstLetter = itemView.findViewById(R.id.txt_firstletter);
        }
    }
}

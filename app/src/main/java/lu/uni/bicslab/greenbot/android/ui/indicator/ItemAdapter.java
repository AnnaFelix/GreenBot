package lu.uni.bicslab.greenbot.android.ui.indicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lu.uni.bicslab.greenbot.android.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

    private List<IndicatorViewModel> celebrityList;

    public ItemAdapter(List<IndicatorViewModel> celebrityList) {
        this.celebrityList = celebrityList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.indicator_item_layout, parent, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        IndicatorViewModel item = celebrityList.get(position);
        holder.txtName.setText(item.getIndicator_name());
        holder.txtDoc.setText(item.getIndicator_name());
    }

    @Override
    public int getItemCount() {
        return celebrityList.size();
    }
}
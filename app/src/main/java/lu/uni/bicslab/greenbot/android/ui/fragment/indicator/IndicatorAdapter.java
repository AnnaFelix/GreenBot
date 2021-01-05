package lu.uni.bicslab.greenbot.android.ui.fragment.indicator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.activity.itemmain.ItemDetailsActivity;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.IndicatorItemHolder> implements Filterable {

    private List<ProductModel> indicatorList;
    private List<ProductModel> indicatorListFiltered;
    private Context context;

    public void setMovieList(Context context, final List<ProductModel> indicatorList) {
        this.context = context;
        if (this.indicatorList == null) {
            this.indicatorList = indicatorList;
            this.indicatorListFiltered = indicatorList;
            notifyItemChanged(0, indicatorListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return IndicatorAdapter.this.indicatorList.size();
                }

                @Override
                public int getNewListSize() {
                    return indicatorList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return IndicatorAdapter.this.indicatorList.get(oldItemPosition).getName() == indicatorList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    ProductModel newdata = IndicatorAdapter.this.indicatorList.get(oldItemPosition);

                    ProductModel olddata = indicatorList.get(newItemPosition);

                    return newdata.getName() == olddata.getName();
                }
            });
            this.indicatorList = indicatorList;
            this.indicatorListFiltered = indicatorList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public IndicatorItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.indicator_item_layout, parent, false);
        return new IndicatorItemHolder(view);
    }

    @Override
    public void onBindViewHolder(IndicatorItemHolder holder, int position) {
        final int pos = position;
        holder.txtName.setText(indicatorListFiltered.get(position).getName());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra("code", indicatorListFiltered.get(pos).getCode());
                intent.putExtra("title", indicatorListFiltered.get(pos).getName());
                context.startActivity(intent);
            }
        });

        Glide.with(context).load(indicatorListFiltered.get(position).getImage_url()).apply(RequestOptions.centerCropTransform()).into(holder.imageview_icon);
    }

    @Override
    public int getItemCount() {

        if (indicatorList != null) {
            return indicatorListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    indicatorListFiltered = indicatorList;
                } else {
                    List<ProductModel> filteredList = new ArrayList<>();
                    for (ProductModel movie : indicatorList) {
                        if (movie.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    indicatorListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = indicatorListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                indicatorListFiltered = (ArrayList<ProductModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    class IndicatorItemHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public CardView card_view;
        public ImageView imageview_icon;

        public IndicatorItemHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            card_view = (CardView) view.findViewById(R.id.card_view);
            imageview_icon = (ImageView) view.findViewById(R.id.imageview_icon);
        }
    }
}


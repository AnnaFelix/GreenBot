package lu.uni.bicslab.greenbot.android.ui.indicator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.activity.ItemDetailsActivity;

public class IndicatorAdapter extends RecyclerView.Adapter<ItemHolder> implements Filterable {

    private List<IndicatorViewModel> movieList;
    private List<IndicatorViewModel> movieListFiltered;
    private Context context;

    public void setMovieList(Context context, final List<IndicatorViewModel> movieList) {
        this.context = context;
        if (this.movieList == null) {
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            notifyItemChanged(0, movieListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return IndicatorAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return IndicatorAdapter.this.movieList.get(oldItemPosition).getIndicator_name() == movieList.get(newItemPosition).getIndicator_name();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    IndicatorViewModel newdata = IndicatorAdapter.this.movieList.get(oldItemPosition);

                    IndicatorViewModel olddata = movieList.get(newItemPosition);

                    return newdata.getIndicator_name() == olddata.getIndicator_name();
                }
            });
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.indicator_item_layout, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder( ItemHolder holder, int position) {
        final int pos = position;
        holder.txtName.setText(movieListFiltered.get(position).getIndicator_name());
        holder.txtDoc.setText(movieListFiltered.get(position).getIndicator_data());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ItemDetailsActivity.class);
                intent.putExtra("position", pos);
                intent.putExtra("title", movieListFiltered.get(pos).getIndicator_name());
                context.startActivity(intent);
            }
        });

        //Glide.with(context).load(movieList.get(position).getImageUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    @Override
    public int getItemCount() {

        if (movieList != null) {
            return movieListFiltered.size();
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
                    movieListFiltered = movieList;
                } else {
                    List<IndicatorViewModel> filteredList = new ArrayList<>();
                    for (IndicatorViewModel movie : movieList) {
                        if (movie.getIndicator_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    movieListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<IndicatorViewModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }
}


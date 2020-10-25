package lu.uni.bicslab.greenbot.android.ui.indicator_category;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.activity.ItemDetailsActivity;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.indicator.IndicatorFragment;

public class IndicatorCategoryAdapter extends RecyclerView.Adapter<ItemHolder> implements Filterable {

    private List<IndicatorCategoryModel> movieList;
    private List<IndicatorCategoryModel> movieListFiltered;
    private Context context;

    public void setMovieList(Context context, final List<IndicatorCategoryModel> movieList) {
        this.context = context;
        if (this.movieList == null) {
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            notifyItemChanged(0, movieListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return IndicatorCategoryAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return IndicatorCategoryAdapter.this.movieList.get(oldItemPosition).getIndicator_name() == movieList.get(newItemPosition).getIndicator_name();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    IndicatorCategoryModel newdata = IndicatorCategoryAdapter.this.movieList.get(oldItemPosition);

                    IndicatorCategoryModel olddata = movieList.get(newItemPosition);

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.indicator_category_item_layout, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder( ItemHolder holder, int position) {
        final int pos = position;
        final IndicatorCategoryModel model = movieListFiltered.get(position);
        holder.txtName.setText(model.getIndicator_name());
        holder.txtDoc.setText(model.getDescription());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context , ItemDetailsActivity.class);
//                intent.putExtra("position", pos);
//                intent.putExtra("title", movieListFiltered.get(pos).getIndicator_name());
//                context.startActivity(intent);
                //getActivity().getSupportFragmentManager();

//                FragmentManager fm = MainActivity.mainActivity.getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//
//                Fragment fragOne = new IndicatorFragment();
//                Bundle arguments = new Bundle();
//                arguments.putString("id", "true");
//                fragOne.setArguments(arguments);
//                ft.add(R.id.detail_fragment_container, fragOne);
//                ft.commit();
                //Toast.makeText(context,""+model.getId(),Toast.LENGTH_LONG).show();
                Log.e("annnnnnnnnnnna",""+model.getId());
                Bundle bundle = new Bundle();
                bundle.putString("value", ""+model.getId());
                Utils.id = model.getId();
                Navigation.findNavController(v).navigate(R.id.goto_indicator, bundle);

            }
        });
       if(model.getIcon_name().equals("Socio-Culturel")){
          holder.imageview_icon.setBackground(ContextCompat.getDrawable(context, R.drawable.socio_culturel_icon));
       }else if(model.getIcon_name().equals("Socio-Economique")){
           holder.imageview_icon.setBackground(ContextCompat.getDrawable(context, R.drawable.socio_economique_icon));

       }else if(model.getIcon_name().equals("Socio-Ecologique")){
           holder.imageview_icon.setBackground(ContextCompat.getDrawable(context, R.drawable.socio_ecologique_icon));

       }else {
           holder.imageview_icon.setBackground(ContextCompat.getDrawable(context, R.drawable.socio_politique_icon));

       }


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
                    List<IndicatorCategoryModel> filteredList = new ArrayList<>();
                    for (IndicatorCategoryModel movie : movieList) {
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
                movieListFiltered = (ArrayList<IndicatorCategoryModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }
}


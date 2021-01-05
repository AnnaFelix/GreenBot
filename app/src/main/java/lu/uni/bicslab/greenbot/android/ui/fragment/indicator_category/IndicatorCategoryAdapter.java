package lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Utils;

public class IndicatorCategoryAdapter extends RecyclerView.Adapter<ItemHolder> implements Filterable {


    private List<IndicatorCategoryModel> modelList;
    private List<IndicatorCategoryModel> modelListFiltered;
    private Context context;


    public void setModelList(Context context, final List<IndicatorCategoryModel> movieList) {
        this.context = context;
        if (this.modelList == null) {
            this.modelList = movieList;
            this.modelListFiltered = movieList;
            notifyItemChanged(0, modelListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return IndicatorCategoryAdapter.this.modelList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return IndicatorCategoryAdapter.this.modelList.get(oldItemPosition).getIndicator_name() == movieList.get(newItemPosition).getIndicator_name();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    IndicatorCategoryModel newdata = IndicatorCategoryAdapter.this.modelList.get(oldItemPosition);

                    IndicatorCategoryModel olddata = movieList.get(newItemPosition);

                    return newdata.getIndicator_name() == olddata.getIndicator_name();
                }
            });
            this.modelList = movieList;
            this.modelListFiltered = movieList;
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
        final IndicatorCategoryModel model = modelListFiltered.get(position);
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

        if (modelList != null) {
            return modelListFiltered.size();
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
                    modelListFiltered = modelList;
                } else {
                    List<IndicatorCategoryModel> filteredList = new ArrayList<>();
                    for (IndicatorCategoryModel movie : modelList) {
                        if (movie.getIndicator_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    modelListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = modelListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                modelListFiltered = (ArrayList<IndicatorCategoryModel>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }
}


package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.CompareModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category.IndicatorCategoryModel;

public class ViewSliderPagerAdapter extends RecyclerView.Adapter<ViewSliderPagerAdapter.SliderViewHolder> {
    Context mcontext;
    CustomCompareGridAdapter adapter;
    List<IndicatorCategoryModel> mCategoryList;
    ArrayList<Integer> layouts;

    List<CompareModel> compareModelList;

    public ViewSliderPagerAdapter(Context mcontext,
                                  ArrayList<Integer> layouts, List<IndicatorCategoryModel> mIndicatorCategoryModel, List<CompareModel> mCategoryList) {
        this.mcontext = mcontext;
        this.compareModelList = mCategoryList;
        this.mCategoryList = mIndicatorCategoryModel;
        this.layouts = layouts;
    }

    @NonNull
    @Override
    public ViewSliderPagerAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);

        return new ViewSliderPagerAdapter.SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        //ProductModel model = mProductToReviewlist.get(position);
        holder.txt_categoryname.setText(mCategoryList.get(position).getIndicator_name());
        Log.e("dddd", "" + position + mCategoryList.get(position).getIndicator_name());
        Glide.with(mcontext).load(mCategoryList.get(position).getIcon_name()).
                apply(RequestOptions.centerCropTransform()).into(holder.img_product_icon);

        holder.recycler_viewindicator.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2);
        holder.recycler_viewindicator.setLayoutManager(gridLayoutManager);
        holder.recycler_viewindicator.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomCompareGridAdapter(mcontext, position, compareModelList);
        holder.recycler_viewindicator.setAdapter(adapter);
    }

    @Override
    public int getItemViewType(int position) {
        return layouts.get(position);
    }

    @Override
    public int getItemCount() {
        return layouts.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product_icon;
        RecyclerView recycler_viewindicator;
        TextView txt_categoryname;

        public SliderViewHolder(View view) {
            super(view);

            img_product_icon = (ImageView) view.findViewById(R.id.img_product_icon);
            recycler_viewindicator = (RecyclerView) view.findViewById(R.id.recycler_viewindicator);
            txt_categoryname = (TextView) view.findViewById(R.id.txt_categoryname);
        }
    }
}
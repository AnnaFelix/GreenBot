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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.CustomGridAdapter;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category.IndicatorCategoryModel;

public  class ViewSliderPagerAdapter extends RecyclerView.Adapter<ViewSliderPagerAdapter.SliderViewHolder> {
    Context mcontext;
    CustomCompareAdapter adapter;
    List<IndicatorCategoryModel> mCategoryList;
    ArrayList<Integer> layouts;
    List<ProductModel> productList;
    public ViewSliderPagerAdapter(Context mcontext,
                                  List<IndicatorCategoryModel> mCategoryList, ArrayList<Integer> layouts) {
        this. mcontext = mcontext;
        this. mCategoryList = mCategoryList;
        this. layouts =  layouts;
        Log.e("dddd size",""+layouts.size());
        String jsonFileStringProduct = Utils.getJsonFromAssets(mcontext, "products.json");
        Gson gson = new Gson();
        Type listUserTypeProduct = new TypeToken<List<ProductModel>>() { }.getType();
        productList = gson.fromJson(jsonFileStringProduct,listUserTypeProduct);

    }


    @NonNull
    @Override
    public ViewSliderPagerAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        Log.e("dddd",""+viewType);

        return new ViewSliderPagerAdapter.SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        //ProductModel model = mProductToReviewlist.get(position);
        holder.txt_categoryname.setText(mCategoryList.get(position).getIndicator_name());
        Log.e("dddd",""+position);
        Glide.with(mcontext).load(mCategoryList.get(position).getIcon_name()).
                apply(RequestOptions.centerCropTransform()).into(holder.img_product_icon);

        holder.recycler_viewindicator.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2);
        holder.recycler_viewindicator.setLayoutManager(gridLayoutManager);
        holder.recycler_viewindicator.setItemAnimator(new DefaultItemAnimator());
        adapter = new CustomCompareAdapter(mcontext,productList);
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
package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.CompareModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;

public class CustomCompareGridAdapter extends RecyclerView.Adapter<CustomCompareGridAdapter.CustomViewHolder> {

    private List<CompareModel> compareModel;
    private Context mcontext;
    int positionViewpager;
    private List<IndicatorModel> modelIndicatorModel;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView txt_categoryname;
        ImageView img_product_icon;
        RecyclerView recycler_viewindicator;

        public CustomViewHolder(View view) {
            super(view);
            this.txt_categoryname = (TextView) view.findViewById(R.id.txt_categoryname);
            this.img_product_icon = (ImageView) view.findViewById(R.id.img_product_icon);
            this.recycler_viewindicator = (RecyclerView) view.findViewById(R.id.indicator_view);
        }
    }

    public CustomCompareGridAdapter(Context mcontext, int positionViewpager, List<CompareModel> mCategoryList) {
        this.compareModel = mCategoryList;
        this.mcontext = mcontext;
        this.positionViewpager = positionViewpager;
        if (positionViewpager == 0) {
            modelIndicatorModel = compareModel.get(0).getmCompareItemsModel().getIndCatEnvironmentlist();

        } else if (positionViewpager == 1) {
            modelIndicatorModel = compareModel.get(0).getmCompareItemsModel().getIndCatEconomicList();

        } else if (positionViewpager == 2) {
            modelIndicatorModel = compareModel.get(0).getmCompareItemsModel().getIndCatSociallist();

        } else {
            modelIndicatorModel = compareModel.get(0).getmCompareItemsModel().getIndCatGoodGevernanceList();

        }
    }

    @Override
    public CustomCompareGridAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comare_row, parent, false);

        CustomCompareGridAdapter.CustomViewHolder myViewHolder = new CustomCompareGridAdapter.CustomViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomCompareGridAdapter.CustomViewHolder holder, int position) {
        // ProductModel dataModel = productModel.get(position);
        ImageView imageview_icon = holder.img_product_icon;
        RecyclerView recycler_viewindicator = holder.recycler_viewindicator;
        Log.e("eee position", "" + positionViewpager);
        //imageview_icon.setBackground(dataModel.get(position).getImage());
        Glide.with(mcontext).load(compareModel.get(position).getProductModelForcompare().getImage_url()).
                apply(RequestOptions.centerCropTransform()).into(imageview_icon);

        CustomCompareListRowAdapter adapter = new CustomCompareListRowAdapter(mcontext, positionViewpager, modelIndicatorModel);
        recycler_viewindicator.setAdapter(adapter);
        recycler_viewindicator.setLayoutManager(new LinearLayoutManager(mcontext));

    }

    @Override
    public int getItemCount() {
        return compareModel.size();
    }
}
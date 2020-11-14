package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.CustomAdapter;
import lu.uni.bicslab.greenbot.android.other.CustomGridAdapter;
import lu.uni.bicslab.greenbot.android.other.UpdateFeedbackListener;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.selectgrid.SelectLocalImportModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;

public class CustomCompareAdapter extends RecyclerView.Adapter<CustomCompareAdapter.CustomViewHolder> {

    private List<ProductModel> dataModel;
    private Context mcontext;

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

    public CustomCompareAdapter(Context mcontext,List<ProductModel> data) {
        this.dataModel = data;
        this.mcontext = mcontext;
    }

    @Override
    public CustomCompareAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comare_row, parent, false);

        CustomCompareAdapter.CustomViewHolder myViewHolder = new CustomCompareAdapter.CustomViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomCompareAdapter.CustomViewHolder  holder, final int position) {

        ImageView imageview_icon = holder.img_product_icon;
        RecyclerView recycler_viewindicator = holder.recycler_viewindicator;
        Log.e("eee",""+dataModel.get(position).getIndicators().size());
        //imageview_icon.setBackground(dataModel.get(position).getImage());
        Glide.with(mcontext).load(dataModel.get(position).getImage_url()).
                apply(RequestOptions.centerCropTransform()).into(imageview_icon);
        CustomCompareRowAdapter adapter = new CustomCompareRowAdapter(mcontext, (dataModel.get(position).getIndicators()));
        recycler_viewindicator.setAdapter(adapter);
        recycler_viewindicator.setLayoutManager(new LinearLayoutManager(mcontext));

    }
    @Override
    public int getItemCount() {
        return dataModel.size();
    }
}
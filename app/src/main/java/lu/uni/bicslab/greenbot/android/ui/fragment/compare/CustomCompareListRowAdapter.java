package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;

public class CustomCompareListRowAdapter extends RecyclerView.Adapter<CustomCompareListRowAdapter.CompareCustomView> {

    private List<IndicatorModel> indicatorModel;
    private Context context;

    public CustomCompareListRowAdapter(Context context, int positionViewpager, List<IndicatorModel> indicatorModel) {
        this.context = context;
        this.indicatorModel = indicatorModel;
        Log.e("sizzee", "" + indicatorModel.size());

    }

    @Override
    public CustomCompareListRowAdapter.CompareCustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comare_item_row, parent, false);
        return new CustomCompareListRowAdapter.CompareCustomView(view);
    }

    @Override
    public void onBindViewHolder(CustomCompareListRowAdapter.CompareCustomView holder, int position) {
        IndicatorModel model = indicatorModel.get(position);
        Log.e("model", "" + model.getName());

        holder.mName.setText(model.getName());
        holder.layout_main_compare.setVisibility(View.INVISIBLE);

        Glide.with(context).load(Utils.GetImage(context, model.getIcon_name())).apply(RequestOptions.centerCropTransform()).into(holder.txt_firstletter);
        if (model.isSelected() == false) {
            holder.mName.setTextColor(Color.GRAY);
            // Apply grayscale filter
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            holder.txt_firstletter.setColorFilter(filter);
        } else {
            // holder.layout_main_compare.setVisibility(View.GONE);
            holder.mName.setTextColor(Color.BLACK);

        }
    }

    @Override
    public int getItemCount() {
        Log.e("model", "" + indicatorModel.size());
        return indicatorModel == null ? 0 : indicatorModel.size();
    }

    public static class CompareCustomView extends RecyclerView.ViewHolder {

        private TextView mName;
        private ImageView txt_firstletter;
        private RelativeLayout layout_main_compare;

        public CompareCustomView(View itemView) {
            super(itemView);

            Log.e("model", "inside");
            mName = itemView.findViewById(R.id.txt_name);
            txt_firstletter = itemView.findViewById(R.id.txt_firstletter);
            layout_main_compare = itemView.findViewById(R.id.layout_main_compare);
        }
    }
}

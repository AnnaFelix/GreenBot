package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;

public class CustomCompareRowAdapter extends RecyclerView.Adapter<CustomCompareRowAdapter.CompareCustomView> {

    private List<IndicatorModel> indicatorModel;
    private Context context;

    public CustomCompareRowAdapter(Context context, List<IndicatorModel>  indicatorModel) {
        this.context = context;
        this.indicatorModel = indicatorModel;
        Log.e("sizzee",""+indicatorModel.size());

    }

    @Override
    public CustomCompareRowAdapter.CompareCustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comare_item_row, parent, false);
        return new CustomCompareRowAdapter.CompareCustomView(view);
    }

    @Override
    public void onBindViewHolder(CustomCompareRowAdapter.CompareCustomView holder, int position) {
        IndicatorModel model = indicatorModel.get(position);
        Log.e("model",""+model.getName());

        holder.mName.setText(model.getName());

        Glide.with(context).load(Utils.GetImage(context,model.getIcon_name())).apply(RequestOptions.centerCropTransform()).into(holder.txt_firstletter);

    }

    @Override
    public int getItemCount() {
        Log.e("model",""+indicatorModel.size());
        return indicatorModel == null ? 0 : indicatorModel.size();
    }

    public static class CompareCustomView extends RecyclerView.ViewHolder {

        private TextView mName;
        private ImageView txt_firstletter;

        public CompareCustomView(View itemView) {
            super(itemView);

            Log.e("model","inside");
            mName = itemView.findViewById(R.id.txt_name);
            txt_firstletter = itemView.findViewById(R.id.txt_firstletter);
        }
    }
}

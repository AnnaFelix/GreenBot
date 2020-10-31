package lu.uni.bicslab.greenbot.android.ui.activity.feedback;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class CustomindicatorAdapter extends RecyclerView.Adapter<CustomindicatorAdapter.CustomView> {

    private List<IndicatorModel> indicatorModel;
    private Context context;

    public CustomindicatorAdapter(Context context, List<IndicatorModel>  indicatorModel) {
        this.context = context;
        this.indicatorModel = indicatorModel;

    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feedback_row, parent, false);
        return new CustomindicatorAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(CustomView holder, int position) {
        IndicatorModel data = indicatorModel.get(position);

        Drawable img = Utils.GetImage(context,data.getIcon_name());
        Glide.with(context).load(img).apply(RequestOptions.centerCropTransform()).into(holder.text_title);

    }

    @Override
    public int getItemCount() {
        return indicatorModel == null ? 0 : indicatorModel.size();
    }

    public static class CustomView extends RecyclerView.ViewHolder {

        private ImageView text_title;



        public CustomView(View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.text_title);

        }
    }
}

package lu.uni.bicslab.greenbot.android.ui.activity.feedback;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.UpdateFeedbackListener;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;

public class CustomindicatorAdapter extends RecyclerView.Adapter<CustomindicatorAdapter.CustomView> {

   // private List<IndicatorModel> indicatorModel;
    private Context context;
    IndicatorModel data;
    List<ProductModel> mProductToReviewlist;
    UpdateFeedbackListener mUpdateFeedbackListener;
    int currentViewpagerPos;
    public CustomindicatorAdapter(Context context, List<ProductModel> mProductToReviewlist, int currentViewpagerPos, UpdateFeedbackListener mUpdateFeedbackListener) {
        this.context = context;
        this.mProductToReviewlist = mProductToReviewlist;
       // this.indicatorModel = mProductToReviewlist.get(currentViewpagerPos).getIndicators();
        this.mUpdateFeedbackListener = mUpdateFeedbackListener;
        this.currentViewpagerPos = currentViewpagerPos;
    }

    @Override
    public CustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.feedback_row, parent, false);
        return new CustomindicatorAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(final CustomView holder, int positionval) {
         this. data = mProductToReviewlist.get(currentViewpagerPos).getIndicators().get(positionval);

        Drawable img = Utils.GetImage(context,data.getIcon_name());
        Glide.with(context).load(img).apply(RequestOptions.centerCropTransform()).into(holder.text_title);
        int clickPos = data.getSelectionnumber();
        if(clickPos == 1){
           holder.item_row.setBackgroundColor(Color.BLUE);
        }else if(clickPos == 2 ){
            holder.item_row.setBackgroundColor(Color.GREEN);
        }else {
            holder.item_row.setBackgroundColor(Color.GRAY);
        }
        holder.txt_pos.setText(""+positionval);
        holder.icon_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                   //int clickPos = data.getSelectionnumber();


             }
        });
        holder.layout_main.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position = Integer.parseInt(""+holder.txt_pos.getText().toString());
               IndicatorModel data = mProductToReviewlist.get(currentViewpagerPos).getIndicators().get(position);
               int clickPos = data.getSelectionnumber();
               //check click
               boolean oneadded = false;
               boolean twoadded = false;
               int i =0;
               int oneselectedPos =0;
               int twoselectedPos =0;
               for (IndicatorModel model : mProductToReviewlist.get(currentViewpagerPos).getIndicators()) {
                   if (model.getSelectionnumber() == 1) {
                       oneadded = true;
                       oneselectedPos = i;
                   }
                   if (model.getSelectionnumber() == 2) {
                       twoadded = true;
                       twoselectedPos = i;
                   }
                   i++;
               }//

               if (clickPos == 0) {

                   if (oneadded == true && twoadded == true) {
                       //no selection possible
                       Toast.makeText(context, "Max 2 only", Toast.LENGTH_SHORT).show();
                       data.setSelectionnumber(0);
                       IndicatorModel modeldata = data;
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);

                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.gray_dark));
                       holder.icon_select.setChecked(false);
                       //  FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                   } else if (oneadded == true && twoadded == false) {
                       //set 2nd selection
                       data.setSelectionnumber(2);
                       IndicatorModel modeldata = data;
                       //  indicatorModel.set(position, modeldata);
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);
                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.lightgreen));
                       //  FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                   } else {
                       data.setSelectionnumber(1);
                       IndicatorModel modeldata = data;
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);
                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.blue));
                       // FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                   }

               } else if (clickPos == 2 || clickPos == 1) {
                   if (oneadded == true && twoadded == false && clickPos != 1) {
                       data.setSelectionnumber(2);
                       IndicatorModel modeldata = data;
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);
                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.lightgreen));
                       // FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                   } else if (oneadded == true && twoadded == false && clickPos == 1) {
                       data.setSelectionnumber(0);
                       IndicatorModel modeldata = data;
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);
                       // FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.gray_dark));

                   } else if (oneadded == true && twoadded == true && clickPos == 1) {
                       data.setSelectionnumber(0);
                       IndicatorModel modeldata = data;
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);
                       //FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                       //two pos
                       IndicatorModel modeldatatwo =  mProductToReviewlist.get(currentViewpagerPos).getIndicators().get(twoselectedPos);
                       modeldatatwo.setSelectionnumber(1);
                       //indicatorModel.set(twoselectedPos, modeldatatwo);
                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.blue));
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(twoselectedPos, modeldatatwo);

                       // FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(twoselectedPos);

                   } else if (oneadded == true && twoadded == true && clickPos == 2) {
                       data.setSelectionnumber(0);
                       IndicatorModel modeldata = data;
                       mProductToReviewlist.get(currentViewpagerPos).getIndicators().set(position, modeldata);
                       // FeedbackMainActivity.ViewsSliderAdapter.adapter.notifyItemChanged(position);
                       holder.icon_select.setButtonTintList(context.getColorStateList(R.color.gray_dark));

                   }
               }
               mUpdateFeedbackListener.updateFeedbackAction(true, mProductToReviewlist.get(currentViewpagerPos).getIndicators(), currentViewpagerPos, position);

            }
    });
    }

    @Override
    public int getItemCount() {
        return mProductToReviewlist.get(currentViewpagerPos).getIndicators() == null ? 0 : mProductToReviewlist.get(currentViewpagerPos).getIndicators().size();
    }

    public static class CustomView extends RecyclerView.ViewHolder {

        private ImageView text_title;
        private CheckBox icon_select;
        private RelativeLayout item_row;
        private TextView txt_pos;
        private RelativeLayout layout_main;



        public CustomView(View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.text_title);
            icon_select = itemView.findViewById(R.id.icon_select);
            item_row = itemView.findViewById(R.id.item_row);
            txt_pos = itemView.findViewById(R.id.txt_pos);
            layout_main = itemView.findViewById(R.id.layout_main);

        }
    }
}

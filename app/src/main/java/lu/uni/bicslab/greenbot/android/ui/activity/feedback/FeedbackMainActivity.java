package lu.uni.bicslab.greenbot.android.ui.activity.feedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.databinding.FeedbackMainBinding;
import lu.uni.bicslab.greenbot.android.other.UpdateActionCompleteListener;
import lu.uni.bicslab.greenbot.android.other.UpdateFeedbackListener;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;

//feedback view
public class FeedbackMainActivity extends AppCompatActivity implements UpdateActionCompleteListener {
    private FeedbackMainActivity.ViewsSliderAdapter mAdapter;
    private TextView[] dots;
    static ArrayList<Integer> layouts;
    private FeedbackMainBinding binding;
    List<ProductModel> mProductToReviewlist;

    UpdateActionCompleteListener mUpdateActionCompleteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FeedbackMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mUpdateActionCompleteListener = this;
        readData();

    }

    private void init() {
        // layouts of all welcome sliders
        // add few more layouts if you want
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setmAdapterViewpager();
    }

    public void setmAdapterViewpager() {
        if (mProductToReviewlist.size() == 0) {
            finish();
        } else {
            layouts = new ArrayList<Integer>();
            for (int i = 0; i < mProductToReviewlist.size(); i++) {
                layouts.add(R.layout.feedback_row_layout);
            }

            mAdapter = new FeedbackMainActivity.ViewsSliderAdapter(FeedbackMainActivity.this, mProductToReviewlist, mUpdateActionCompleteListener);
            binding.viewPager.setAdapter(mAdapter);
            binding.viewPager.registerOnPageChangeCallback(pageChangeCallback);

            binding.btnNext.setVisibility(View.GONE);
            binding.btnSkip.setVisibility(View.GONE);
            binding.viewPager.setUserInputEnabled(false);

            // adding bottom dots
            addBottomDots(0);
        }
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        //Toast.makeText(this, "slides_ended", Toast.LENGTH_LONG).show();
        finish();
        Intent i = new Intent(this, SigninActivity.class);
        startActivity(i);
    }

    /*
     * ViewPager page change listener
     */
    ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == 0) {
                // last page. make button text to GOT IT
                binding.btnNext.setText("start");
                binding.btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                binding.btnNext.setText("next");
                binding.btnSkip.setVisibility(View.VISIBLE);
            }
        }
    };

    /*
     * Adds bottom dots indicator
     * */
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.size()];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    @Override
    public void updateAction(boolean isUpdated) {
        setmAdapterViewpager();
    }


    public static class ViewsSliderAdapter extends RecyclerView.Adapter<FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder> implements UpdateFeedbackListener {
        Context mcontext;
        UpdateActionCompleteListener mUpdateActionCompleteListener;
        List<ProductModel> mProductToReviewlist;
        CustomindicatorAdapter adapter;
        UpdateFeedbackListener mUpdateFeedbackListener;
        int currentViewpagerPos;

        public ViewsSliderAdapter(Context mcontext, List<ProductModel> mProductToReviewlist, UpdateActionCompleteListener mUpdateActionCompleteListener) {
            this.mcontext = mcontext;
            this.mUpdateActionCompleteListener = mUpdateActionCompleteListener;
            this.mProductToReviewlist = mProductToReviewlist;
            mUpdateFeedbackListener = this;
        }

        @Override
        public void updateFeedbackAction(boolean isUpdated, List<IndicatorModel> mIndicatorModel, int pos, int itemposchnged) {
            mProductToReviewlist.get(pos);
            mProductToReviewlist.get(pos).setIndicators(mIndicatorModel);
            adapter.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            return new FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder(view);
        }

        public void onBindViewHolder(@NonNull FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder holder, int position) {
            currentViewpagerPos = position;
            ProductModel model = mProductToReviewlist.get(position);
            //holder.slide_one_title.setText(model.getCategory());
            //holder.slide_one_title.setVisibility(View.INVISIBLE);
            holder.slide_one_doc.setText(model.getDescription());
            holder.txtName.setText(model.getName());
            Glide.with(mcontext).load(model.getImage_url()).apply(RequestOptions.centerCropTransform()).into(holder.img_icon);

            GridLayoutManager gridLayoutManager;
            gridLayoutManager = new GridLayoutManager(mcontext, 2);
            holder.my_recycler_view.setLayoutManager(gridLayoutManager);
            holder.my_recycler_view.setItemAnimator(new DefaultItemAnimator());
            List<IndicatorModel> indicatorslist = model.getIndicators();
            // List<IndicatorModel> indi =  mProductToReviewlist.get(currentViewpagerPos).getIndicators();
            IndicatorModel modeltoadd = new IndicatorModel("indicator_price", "Price", "Price",
                    "indicator_price", "category_id", "id",
                    "general_description", 0, false);
            mProductToReviewlist.get(currentViewpagerPos).getIndicators().add(modeltoadd);
            IndicatorModel modeltoaddedittext = new IndicatorModel("indicator_review", "Review", "Review",
                    "indicator_Review", "category_id", "id",
                    "general_description", 0, false);
            mProductToReviewlist.get(currentViewpagerPos).getIndicators().add(modeltoaddedittext);


            //UpdateFeedbackListener mUpdateFeedbackListener;
            adapter = new CustomindicatorAdapter(mcontext, mProductToReviewlist, currentViewpagerPos, mUpdateFeedbackListener);
            holder.my_recycler_view.setAdapter(adapter);
            holder.btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProductToReviewlist.remove(currentViewpagerPos);
                    if (mProductToReviewlist.size() > 0) {
                        for (ProductModel model : mProductToReviewlist) {
                            for (IndicatorModel indicator : model.getIndicators()) {
                                indicator.setSelectionnumber(0);
                            }
                        }
                    }
                    mUpdateActionCompleteListener.updateAction(true);
                }
            });
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
            public TextView slide_one_title, slide_one_doc, txtName;
            ImageView img_icon;
            RecyclerView my_recycler_view;
            Button btn_start;

            public SliderViewHolder(View view) {
                super(view);

                slide_one_title = (TextView) view.findViewById(R.id.slide_one_title);
                slide_one_doc = (TextView) view.findViewById(R.id.slide_one_doc);
                txtName = (TextView) view.findViewById(R.id.txtName);
                img_icon = (ImageView) view.findViewById(R.id.img_icon);
                my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                btn_start = (Button) view.findViewById(R.id.btn_start);
            }
        }
    }

    private void readData() {
        String jsonFileString = lu.uni.bicslab.greenbot.android.other.Utils.getJsonFromAssets(getApplicationContext(), "products_to_review.json");
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<ProductToReview>>() {
        }.getType();
        List<ProductToReview> mProductToReview = gson.fromJson(jsonFileString, listUserType);
        String jsonFileStringProduct = lu.uni.bicslab.greenbot.android.other.Utils.getJsonFromAssets(getApplicationContext(), "products.json");
        Type listUserTypeProduct = new TypeToken<List<ProductModel>>() {
        }.getType();
        String jsonFileStringIndicator = Utils.getJsonFromAssets(getApplicationContext(), "indicators.json");
        Type listUserTypeIndicator = new TypeToken<List<IndicatorModel>>() {
        }.getType();

        List<ProductModel> productList = gson.fromJson(jsonFileStringProduct, listUserTypeProduct);
        List<IndicatorModel> indicatorCategoryList = gson.fromJson(jsonFileStringIndicator, listUserTypeIndicator);

        List<IndicatorModel> indicatorlist = new ArrayList<IndicatorModel>();
        for (IndicatorModel c : indicatorCategoryList) {
            if (indicatorlist.equals(c.getCategory_id())) {
                indicatorlist.add(c);
            }
        }
        mProductToReviewlist = new ArrayList<ProductModel>();

        for (ProductToReview review : mProductToReview) {
            for (ProductModel model : productList) {
                if (review.getProduct_ean().equals(model.getCode())) {
                    mProductToReviewlist.add(model);
                    break;
                }
            }
        }
        int i = 0;
        for (ProductModel c : mProductToReviewlist) {
            List<IndicatorModel> mIndicatorModel = new ArrayList<IndicatorModel>();
            for (IndicatorModel indicaor : c.getIndicators()) {
                for (IndicatorModel indicaormain : indicatorCategoryList) {
                    if (indicaor.getIndicator_idForProduct().equals(indicaormain.getId())) {
                        mIndicatorModel.add(indicaormain);
                    }
                }

            }
            mProductToReviewlist.get(i).setIndicators(mIndicatorModel);
            i++;
        }

        init();

    }
}
package lu.uni.bicslab.greenbot.android.ui.activity.feedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;


public class FeedbackMainActivity extends AppCompatActivity {
    private FeedbackMainActivity.ViewsSliderAdapter mAdapter;
    private TextView[] dots;
    private ArrayList<Integer> layouts;
    private FeedbackMainBinding binding;
    List<ProductModel> mProductToReviewlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FeedbackMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        readData();
    }

    private void init() {
        // layouts of all welcome sliders
        // add few more layouts if you want
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        layouts = new ArrayList<Integer>();
        for(int i=0;i<mProductToReviewlist.size();i++){
            layouts.add(R.layout.feedback_row_layout);
        }

        mAdapter = new FeedbackMainActivity.ViewsSliderAdapter(getApplicationContext());
        binding.viewPager.setAdapter(mAdapter);
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback);
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                launchHomeScreen();

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.size()) {
                    // move to next screen
                    binding.viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }

            }
        });
        // adding bottom dots
        addBottomDots(0);
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


    public class ViewsSliderAdapter extends RecyclerView.Adapter<FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder> {
        Context mcontext;
        public ViewsSliderAdapter(Context mcontext) {
            this. mcontext = mcontext;
        }

        @NonNull
        @Override
        public FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            return new FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder(view);
        }

        public void onBindViewHolder(@NonNull FeedbackMainActivity.ViewsSliderAdapter.SliderViewHolder holder, int position) {
            ProductModel model = mProductToReviewlist.get(position);
            holder.slide_one_title.setText(model.getCategory());
            holder.slide_one_doc.setText(model.getDescription());
            holder.txtName.setText(model.getName()+model.getIndicators().get(0).getId());
            Glide.with(getApplicationContext()).load(model.getImage_url()).apply(RequestOptions.centerCropTransform()).into(holder.img_icon);

            GridLayoutManager gridLayoutManager;
            gridLayoutManager = new GridLayoutManager(mcontext, 2);
            holder.my_recycler_view.setLayoutManager(gridLayoutManager);
            holder.my_recycler_view.setItemAnimator(new DefaultItemAnimator());
            holder.my_recycler_view.setAdapter(new CustomindicatorAdapter(mcontext,model.getIndicators()));
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
            public SliderViewHolder(View view) {
                super(view);

                slide_one_title = (TextView) view.findViewById(R.id.slide_one_title);
                slide_one_doc = (TextView) view.findViewById(R.id.slide_one_doc);
                txtName = (TextView) view.findViewById(R.id.txtName);
                img_icon = (ImageView) view.findViewById(R.id.img_icon);
                my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
            }
        }
    }
    private void readData(){
        String jsonFileString = lu.uni.bicslab.greenbot.android.other.Utils.getJsonFromAssets(getApplicationContext(), "products_to_review.json");
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<ProductToReview>>() { }.getType();
        List<ProductToReview> mProductToReview = gson.fromJson(jsonFileString, listUserType);
        String jsonFileStringProduct = lu.uni.bicslab.greenbot.android.other.Utils.getJsonFromAssets(getApplicationContext(), "products.json");
        Type listUserTypeProduct = new TypeToken<List<ProductModel>>() { }.getType();
        String jsonFileStringIndicator = Utils.getJsonFromAssets(getApplicationContext(), "indicators.json");
        Type listUserTypeIndicator = new TypeToken<List<IndicatorModel>>() { }.getType();

        List<ProductModel> productList = gson.fromJson(jsonFileStringProduct,listUserTypeProduct);
        List<IndicatorModel> indicatorCategoryList = gson.fromJson(jsonFileStringIndicator, listUserTypeIndicator);

        List<IndicatorModel> indicatorlist = new ArrayList<IndicatorModel>();
        for (IndicatorModel c : indicatorCategoryList) {
            if (indicatorlist.equals(c.getCategory_id())) {
                indicatorlist.add(c);
            }
        }
        mProductToReviewlist = new ArrayList<ProductModel>();

        for(ProductToReview review : mProductToReview){
            for (ProductModel model : productList) {
                if(review.getProduct_ean().equals(model.getCode())){
                    mProductToReviewlist.add(model);
                    break;
                }
            }
        }

        for (ProductModel c : mProductToReviewlist) {
            List<IndicatorModel> mIndicatorModel = new ArrayList<IndicatorModel>();
            for(IndicatorModel indicaor : c.getIndicators()) {
                for(IndicatorModel indicaormain:indicatorCategoryList) {
                    if(indicaor.getIndicator_idForProduct().equals(indicaormain.getId())){
                        mIndicatorModel.add(indicaormain);
                    }
                }

            }
            c.setIndicators(mIndicatorModel);
        }

        init();

    }
}
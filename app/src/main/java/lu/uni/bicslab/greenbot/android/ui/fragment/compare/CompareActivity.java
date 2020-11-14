package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.databinding.FeedbackMainBinding;
import lu.uni.bicslab.greenbot.android.databinding.FragmentComareLayoutBinding;
import lu.uni.bicslab.greenbot.android.databinding.OnbordingMainLayoutBinding;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.feedback.FeedbackMainActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.feedback.ProductToReview;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.IndicatorModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator.ProductModel;
import lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category.IndicatorCategoryModel;

public class CompareActivity extends AppCompatActivity {

    private CompareViewModel compareViewModel;
    private ViewSliderPagerAdapter mAdapter;
    private TextView[] dots;
    static ArrayList<Integer> layouts;
    List<ProductModel> mProductToReviewlist;
    private FragmentComareLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentComareLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().setTitle("Compare");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        readData();
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
            dots[i] = new TextView(getApplicationContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
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
        int i=0;
        for (ProductModel c : mProductToReviewlist) {
            List<IndicatorModel> mIndicatorModel = new ArrayList<IndicatorModel>();
            for(IndicatorModel indicaor : c.getIndicators()) {
                for(IndicatorModel indicaormain:indicatorCategoryList) {
                    if(indicaor.getIndicator_idForProduct().equals(indicaormain.getId())){
                        mIndicatorModel.add(indicaormain);
                    }
                }

            }
            mProductToReviewlist.get(i).setIndicators(mIndicatorModel);
            i++;
        }

        //category
        String jsoncategory = Utils.getJsonFromAssets(getApplicationContext(), "indicator_categories.json");
        Log.i("data", jsonFileString);

        Gson gsoncategory = new Gson();
        Type listUserTypecategory = new TypeToken<List<IndicatorCategoryModel>>() { }.getType();

        List<IndicatorCategoryModel> mCategoryListmain = gsoncategory.fromJson(jsoncategory, listUserTypecategory);


        setmAdapterViewpager(mCategoryListmain);

    }
    public void setmAdapterViewpager( List<IndicatorCategoryModel> mCategoryListmain){
        layouts = new ArrayList<Integer>();
        for (int i = 0; i < mCategoryListmain.size(); i++) {
            layouts.add(R.layout.comare_viewpager_row);
        }
            mAdapter = new ViewSliderPagerAdapter(getApplicationContext(), mCategoryListmain, layouts);
            binding.viewPager.setAdapter(mAdapter);
            addBottomDots(0);

    }

}

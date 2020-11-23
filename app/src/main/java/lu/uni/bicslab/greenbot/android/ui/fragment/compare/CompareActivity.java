package lu.uni.bicslab.greenbot.android.ui.fragment.compare;

import android.graphics.Color;
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
import lu.uni.bicslab.greenbot.android.other.CompareModel;
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
    ProductModel modelmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentComareLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Compare");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

         modelmain = (ProductModel)getIntent().getSerializableExtra("key_product");


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
            if(i==0){
                dots[i].setText(Html.fromHtml("&#8226;"));
            }else if(i==1){
                dots[i].setText(Html.fromHtml("&#8226;"));
            }else if(i==2){
                dots[i].setText(Html.fromHtml("&#8226;"));
            }else{
                dots[i].setText(Html.fromHtml("&#8226;"));
            }
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void readDataOld(){
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


        setmAdapterViewpager(mCategoryListmain,null);

    }
    public void setmAdapterViewpager( List<IndicatorCategoryModel> mCategoryListmain, List<CompareModel> mCompareMdellist){
        layouts = new ArrayList<Integer>();
        for (int i = 0; i < mCategoryListmain.size(); i++) {
            layouts.add(R.layout.comare_viewpager_row);
        }
            mAdapter = new ViewSliderPagerAdapter(getApplicationContext(), layouts,mCategoryListmain, mCompareMdellist);
            binding.viewPager.setAdapter(mAdapter);
            addBottomDots(0);

    }

    public void readData(){

        List<IndicatorModel> indicatorCategoryList = Utils.getIndicatorsList(getApplicationContext());

        List<ProductModel> productModelList = Utils.getProductToReview(getApplicationContext());



        //get the items for compare
        List<CompareModel> compareViewModelList = new ArrayList<>();

        for(ProductModel mProductModel : productModelList){
            //main products
            if(modelmain.getType().equals(mProductModel.getType())){
                //add products
                //................................................................

                List<IndicatorModel> indCatEnvironmentlist = new ArrayList<>();
                List<IndicatorModel> indCatSociallist = new ArrayList<>();
                List<IndicatorModel> indCatGoodGevernanceList = new ArrayList<>();
                List<IndicatorModel> indCatEconomicList = new ArrayList<>();
                //................................................................

                for (int j = 0; j < indicatorCategoryList.size(); j++) {
                    IndicatorModel indicatorModelMain = indicatorCategoryList.get(j);

                    //our data
                    if (indicatorModelMain.getCategory_id().equals(Utils.ind_cat_environment)) {
                        indicatorModelMain.setSelected(false);

                        indCatEnvironmentlist.add(indicatorModelMain);

                    } else if (indicatorModelMain.getCategory_id().equals(Utils.ind_cat_social)) {
                        indicatorModelMain.setSelected(false);
                        //if (indicatorModelMain.getId().equals(indicator)) {
                          //  indicatorModelMain.setSelected(true);
                        //}
                        indCatSociallist.add(indicatorModelMain);
                    }else  if (indicatorModelMain.getCategory_id().equals(Utils.ind_cat_good_gevernance)) {
                        indicatorModelMain.setSelected(false);

                        indCatGoodGevernanceList.add(indicatorModelMain);
                    } else if (indicatorModelMain.getCategory_id().equals(Utils.ind_cat_economic)){
                        indicatorModelMain.setSelected(false);

                        indCatEconomicList.add(indicatorModelMain);
                    }

                }
                //................................................................
                CompareModel.CompareItemsModel comparemodel = new CompareModel.CompareItemsModel(indCatEnvironmentlist,
                        indCatEconomicList,indCatSociallist, indCatGoodGevernanceList);
                CompareModel mCompareViewModel = new CompareModel(mProductModel,comparemodel);
                compareViewModelList.add(mCompareViewModel);

                //main
                for (int j = 0; j < mProductModel.getIndicators().size(); j++) {
                    String indicator = mProductModel.getIndicators().get(j).getIndicator_id();
                    String category =  getCategoryIndicator(indicatorCategoryList,indicator);
                    if (category.equals(Utils.ind_cat_environment)) {
                        int n=0;
                        for(IndicatorModel mm:comparemodel.getIndCatEnvironmentlist()){
                            if (mm.getId().equals(indicator)) {
                                comparemodel.getIndCatEnvironmentlist().get(n).setSelected(true);
                            }
                            n++;
                        }

                    } else if (category.equals(Utils.ind_cat_social)) {
                        int n=0;
                        for(IndicatorModel mm:comparemodel.getIndCatSociallist()){
                            if (mm.getId().equals(indicator)) {
                                comparemodel.getIndCatSociallist().get(n).setSelected(true);
                            }
                            n++;
                        }
                    }else  if (category.equals(Utils.ind_cat_good_gevernance)) {
                        int n=0;
                        for(IndicatorModel mm:comparemodel.getIndCatGoodGevernanceList()){
                            if (mm.getId().equals(indicator)) {
                                comparemodel.getIndCatGoodGevernanceList().get(n).setSelected(true);
                            }
                            n++;
                        }
                    } else if (category.equals(Utils.ind_cat_economic)){
                        int n=0;
                        for(IndicatorModel mm:comparemodel.getIndCatEconomicList()){
                            if (mm.getId().equals(indicator)) {
                                comparemodel.getIndCatEconomicList().get(n).setSelected(true);
                            }
                            n++;
                        }
                    }
                }

            }

        }


        //category
        List<IndicatorCategoryModel> mCategoryListmain = Utils.getIndicatorCategoryList(getApplicationContext());


        setmAdapterViewpager(mCategoryListmain,compareViewModelList);

    }
    public String getCategoryIndicator(List<IndicatorModel> model, String id) {
        String category = Utils.ind_cat_environment;
        for (int j = 0; j < model.size(); j++) {
            if (model.get(j).getId().equals(id)) {
                category = model.get(j).getCategory_id();
                break;
            }
        }
        return category;

    }

}

package lu.uni.bicslab.greenbot.android.ui.activity.onbord;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.activity.feedback.FeedbackMainActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.feedback.ProductToReview;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.databinding.OnbordingMainLayoutBinding;
import lu.uni.bicslab.greenbot.android.barcoderead.transformers.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.selectgrid.SelectGridOneActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class OnbordingActivity extends AppCompatActivity {
   // private ViewsSliderAdapter mAdapter;
    private ViewsSliderAdapter mAdapter;
    private TextView[] dots;
    private int[] layouts;
    private OnbordingMainLayoutBinding binding;
    public static boolean init = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OnbordingMainLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.onbording_two_layout,
                R.layout.onbording_one_layout,
                R.layout.onbording_three_layout};

        mAdapter = new ViewsSliderAdapter();
        binding.viewPager.setAdapter(mAdapter);
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback);
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                launchHomeScreen(init);

            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    if(init) {
                        launchHomeScreen(init);
                    }else {
                        binding.viewPager.setCurrentItem(current);
                    }
                }
//                else {
//                    launchHomeScreen();
//                }

            }
        });
        // adding bottom dots
        addBottomDots(0);

        if (init) {
            binding.viewPager.setPageTransformer(Utils.getTransformer(0));
            binding.viewPager.setCurrentItem(0);
            binding.viewPager.getAdapter().notifyDataSetChanged();
        }else{
            binding.viewPager.setPageTransformer(Utils.getTransformer(1));
            binding.viewPager.setCurrentItem(1);
            binding.viewPager.getAdapter().notifyDataSetChanged();
        }//scroll avoid
        if(init) {
            binding.btnSkip.setVisibility(View.GONE);
            binding.viewPager.setUserInputEnabled(false);
        }else {
            binding.btnSkip.setVisibility(View.VISIBLE);
            binding.viewPager.setUserInputEnabled(true);
        }
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen(boolean init) {
        //Toast.makeText(this, "slides_ended", Toast.LENGTH_LONG).show();
        if(init){
            Intent i = new Intent(this, SelectGridOneActivity.class);
            startActivity(i);
        }else{
            Intent i = new Intent(this, SigninActivity.class);
            startActivity(i);
        }
             finish();

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
        dots = new TextView[layouts.length];

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


    public class ViewsSliderAdapter extends RecyclerView.Adapter<ViewsSliderAdapter.SliderViewHolder> {

        public ViewsSliderAdapter() {
        }

        @NonNull
        @Override
        public ViewsSliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            return new SliderViewHolder(view);
        }

        public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            return layouts[position];
        }

        @Override
        public int getItemCount() {
            return layouts.length;
        }

        public class SliderViewHolder extends RecyclerView.ViewHolder {
            public TextView onbord_one_title, onbord_one_doc, slide_two_title,slide_two_doc,  slide_three_title,slide_three_doc;

            public SliderViewHolder(View view) {
                super(view);

                onbord_one_title = (TextView) view.findViewById(R.id.slide_one_title);
                onbord_one_doc = (TextView) view.findViewById(R.id.slide_one_doc);
                slide_two_title = (TextView) view.findViewById(R.id.slide_two_title);
                slide_two_doc = (TextView) view.findViewById(R.id.slide_two_doc);
                slide_three_title = (TextView) view.findViewById(R.id.slide_three_title);
                slide_three_doc = (TextView) view.findViewById(R.id.slide_three_doc);


            }
        }
    }

}
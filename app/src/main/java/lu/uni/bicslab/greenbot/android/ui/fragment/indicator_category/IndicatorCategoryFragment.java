package lu.uni.bicslab.greenbot.android.ui.fragment.indicator_category;

import android.app.SearchManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Utils;

public class IndicatorCategoryFragment extends Fragment
    {
        private RecyclerView recyclerView;

        private IndicatorCategoryAdapter itemAdapter;
        List<IndicatorCategoryModel> list;
        SearchManager searchManager;
        SearchView  searchView;
        TextView textviewloading;
        public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_indicator_category, container, false);
            //ArrayList<IndicatorViewModel> itemList = new ArrayList<>();
            recyclerView = (RecyclerView) root.findViewById(R.id.indicator_view);
            textviewloading = (TextView) root.findViewById(R.id.textviewloading);
            searchView = (SearchView) root.findViewById(R.id.search_src_text);
            searchView.setMaxWidth(Integer.MAX_VALUE);
            itemAdapter = new IndicatorCategoryAdapter();
            list = new ArrayList<IndicatorCategoryModel>();
            list = fillDummyData();


            itemAdapter.setModelList(getActivity(),list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(itemAdapter);


            //searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

            // listening to search query text change
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    // filter recycler view when query submitted
                    itemAdapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    // filter recycler view when text is changed
                    itemAdapter.getFilter().filter(query);
                    return false;
                }
            });

        return root;
    }
        private List<IndicatorCategoryModel> fillDummyData() {
            //List<IndicatorViewModel>
            //data = IndicatorViewModel.prepareDesserts(
            //        getActivity().getResources().getStringArray(R.array.dessert_names),
            //        getActivity().getResources().getStringArray(R.array.dessert_descriptions));
            //return  data;

            textviewloading.setText(R.string.loading);
            String jsonFileString = Utils.getJsonFromAssets(getActivity(), "indicator_categories.json");
            Log.i("data", jsonFileString);

            Gson gson = new Gson();
            Type listUserType = new TypeToken<List<IndicatorCategoryModel>>() { }.getType();

            List<IndicatorCategoryModel> indicatorCategoryList = gson.fromJson(jsonFileString, listUserType);
            //for (int i = 0; i < users.size(); i++) {
            //    Log.i("data", "> Item " + i + "\n" + users.get(i));
            //}


            if(indicatorCategoryList.size()>0){
                textviewloading.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
            }else {
                textviewloading.setVisibility(View.VISIBLE);
                textviewloading.setText(R.string.nodata);
                searchView.setVisibility(View.GONE);
            }
            return indicatorCategoryList;

        }
    }

package lu.uni.bicslab.greenbot.android.ui.indicator;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.gallery.GalleryViewModel;

public class IndicatorFragment extends Fragment

    {
        private RecyclerView recyclerView;

        private IndicatorAdapter itemAdapter;

        SearchManager searchManager;
        SearchView  searchView;
        public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.fragment_indicator, container, false);
            //ArrayList<IndicatorViewModel> itemList = new ArrayList<>();

            //fillDummyData();

            recyclerView = (RecyclerView) root.findViewById(R.id.indicator_view);

            itemAdapter = new IndicatorAdapter();
            itemAdapter.setMovieList(getActivity(),fillDummyData());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(itemAdapter);



            //searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) root.findViewById(R.id.search_src_text);
            searchView.setMaxWidth(Integer.MAX_VALUE);

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
        private List<IndicatorViewModel> fillDummyData() {
            List<IndicatorViewModel>
            data = IndicatorViewModel.prepareDesserts(
                    getActivity().getResources().getStringArray(R.array.dessert_names),
                    getActivity().getResources().getStringArray(R.array.dessert_descriptions));
            return  data;

        }
    }

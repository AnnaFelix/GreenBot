package lu.uni.bicslab.greenbot.android.ui.activity.selectgrid;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.CustomGridAdapter;

public class SelectGridOneActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static ArrayList<SelectLocalImportModel> data;
    private GridLayoutManager gridLayoutManager;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_one_layout);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        btn_start = (Button) findViewById(R.id.btn_start);
        recyclerView.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<SelectLocalImportModel>();
        for (int i = 0; i < SelectLocalImportModel.getTitle(getApplicationContext()).length; i++) {
            data.add(new SelectLocalImportModel(SelectLocalImportModel.getTitle(getApplicationContext())[i],
                    SelectLocalImportModel.getimage(getApplicationContext())[i]

            ));
        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Intent i = new Intent(SelectGridOneActivity.this, SelectIGridTwoActivity.class);
                startActivity(i);
            }
        });
        adapter = new CustomGridAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
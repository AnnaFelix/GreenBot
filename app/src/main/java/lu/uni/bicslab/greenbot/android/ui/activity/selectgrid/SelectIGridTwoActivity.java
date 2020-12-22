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

import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Profile;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.onbord.OnbordStartActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.other.CustomGridAdapter;
import lu.uni.bicslab.greenbot.android.ui.activity.scanitem.SelectscanActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.ui.WaitingPageActivity;

public class SelectIGridTwoActivity extends AppCompatActivity {

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
            data.add(new SelectLocalImportModel(SelectLocalImportModel.getTitle2(getApplicationContext())[i],
                    SelectLocalImportModel.getimage2(getApplicationContext())[i],false

            ));
        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionComlete();

            }
        });
        adapter = new CustomGridAdapter(getApplicationContext(),data);
        recyclerView.setAdapter(adapter);
    }
    void actionComlete(){
        finish();
        Profile profile = Utils.readProfileData(getApplicationContext());
        if(profile!=null && profile.isLogedin()==Utils.user_loggedin){
            Intent i = new Intent(SelectIGridTwoActivity.this, MainActivity.class);
            startActivity(i);
        }else {
            Profile profileData = Utils.readProfileData(getApplicationContext());
            profileData.setLogedin(Utils.user_underconsideration);
            Utils.saveProfile(getApplicationContext(),profileData);
            Intent i = new Intent(SelectIGridTwoActivity.this, WaitingPageActivity.class);
            startActivity(i);
        }
    }
}
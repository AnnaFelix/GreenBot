package lu.uni.bicslab.greenbot.android.ui.activity.selectgrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Profile;
import lu.uni.bicslab.greenbot.android.other.ServerConnection;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.onbord.OnbordStartActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.other.CustomGridAdapter;
import lu.uni.bicslab.greenbot.android.ui.activity.scanitem.SelectscanActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.ui.WaitingPageActivity;

public class SelectIGridTwoActivity extends AppCompatActivity implements ServerConnection.ServerConnectionListner,SelectionActionCompleteListner   {

    private static RecyclerView.Adapter adapter;
    private static RecyclerView recyclerView;
    private static ArrayList<SelectLocalImportModel> data;
    private GridLayoutManager gridLayoutManager;
    private Button btn_start;
    JSONObject jsonObject;
    SelectionActionCompleteListner mSelectionActionCompleteListner;
    ServerConnection.ServerConnectionListner mServerConnectionListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_one_layout);

        mSelectionActionCompleteListner = this;
        mServerConnectionListner = this;
        String datajson = getIntent().getStringExtra("data");
        try {
            jsonObject = new JSONObject(datajson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        btn_start = (Button) findViewById(R.id.btn_start);
        recyclerView.setHasFixedSize(true);


        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<SelectLocalImportModel>();
        for (int i = 0; i < SelectLocalImportModel.getTitle(getApplicationContext()).length; i++) {
            data.add(new SelectLocalImportModel(SelectLocalImportModel.getTitle2(getApplicationContext())[i],
                    SelectLocalImportModel.getimage2(getApplicationContext())[i], false

            ));
        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // actionComlete();
                onpostRequestUserAccess();
            }
        });
        adapter = new CustomGridAdapter(getApplicationContext(), data, mSelectionActionCompleteListner);
        recyclerView.setAdapter(adapter);
    }
    public void onpostRequestUserAccess(){
        //-1 to get the notmodified json object
        ServerConnection.postRequestUserAccess(getApplicationContext(),onJsonObjectSet(-1), mServerConnectionListner);
    }
    void actionComlete() {
        finish();
        Profile profile = Utils.readProfileData(getApplicationContext());
        if (profile != null && profile.isLogedin() == Utils.user_loggedin) {
            Intent i = new Intent(SelectIGridTwoActivity.this, MainActivity.class);
            startActivity(i);
        } else {
          /*  Profile profileData = Utils.readProfileData(getApplicationContext());
            Utils.saveProfile(getApplicationContext(), profileData);*/
            Intent i = new Intent(SelectIGridTwoActivity.this, WaitingPageActivity.class);
            startActivity(i);
        }
    }
    private JSONObject onJsonObjectSet(int position){
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id", jsonObject.get("participant_id"));
            object.put("product_category_1", jsonObject.get("product_category_1"));
            object.put("product_category_2", jsonObject.get("product_category_2"));
            object.put("product_category_3", jsonObject.get("product_category_3"));
            object.put("product_category_4", jsonObject.get("product_category_4"));

            if(position==0){
                object.put("indicator_category_1", getResources().getString(R.string.ind_cat_environment));
            }else{
                object.put("indicator_category_1", jsonObject.get("indicator_category_1"));
            }
            if(position==1){
                object.put("indicator_category_2",  getResources().getString(R.string.ind_cat_social));
            }else{
                object.put("indicator_category_2", jsonObject.get("indicator_category_2"));
            }
            if(position==2){
                object.put("indicator_category_3", getResources().getString(R.string.ind_cat_governance));
            }else{
                object.put("indicator_category_3", jsonObject.get("indicator_category_3"));
            }
            if(position==3){
                object.put("indicator_category_4", getResources().getString(R.string.ind_cat_economic));
            }else{
                object.put("indicator_category_4", jsonObject.get("indicator_category_4"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void onSeclectionCompleted(int position) {
        onJsonObjectSet(position);
    }

    @Override
    public void onServerConnectionActionComplete(String value) {
        actionComlete();
    }
}
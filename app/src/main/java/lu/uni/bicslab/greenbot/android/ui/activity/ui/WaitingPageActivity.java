package lu.uni.bicslab.greenbot.android.ui.activity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Profile;
import lu.uni.bicslab.greenbot.android.other.ServerConnection;
import lu.uni.bicslab.greenbot.android.other.Utils;


public class WaitingPageActivity extends AppCompatActivity implements ServerConnection.ServerConnectionListner {
    ServerConnection.ServerConnectionListner mServerConnection;
    Button btn_start;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getSupportActionBar().hide();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //      WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_waitingpage_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mServerConnection = this;
        btn_start = findViewById(R.id.btn_start);


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Profile profileData = Utils.readProfileData(getApplicationContext());
                ServerConnection.getDataFetchUserStatus(mServerConnection, getApplicationContext(), profileData.getSerialscanner());

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

    }

    @Override
    public void onServerConnectionActionComplete(String value) {
       //requested status to valid - 1st time so set user_loggedin_firsttime
        if(value.equalsIgnoreCase(getResources().getString(R.string.valid))) {

            Profile profileData = Utils.readProfileData(getApplicationContext());
            profileData.setLogedin(Utils.user_loggedin_firsttime);
            Utils.saveProfile(getApplicationContext(), profileData);
            finish();

        }
    }
}

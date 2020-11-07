package lu.uni.bicslab.greenbot.android.ui.activity.onbord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Profile;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.ui.WaitingPageActivity;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class OnbordStartActivity extends AppCompatActivity {

    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_onbord_start);
        btn_start = (Button) findViewById(R.id.btn_start);


        // Set up the user interaction to manually show or hide the system UI.
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(OnbordStartActivity.this, SelectImpotlocalFragment.class);
                Profile profile = Utils.readProfileData(getApplicationContext());
                if(profile!=null && profile.isLogedin()==Utils.user_loggedin){
                    Intent i = new Intent(OnbordStartActivity.this, MainActivity.class);
                    startActivity(i);
                }else if(profile!=null &&  profile.isLogedin()==Utils.user_underconsideration){
                    Intent i = new Intent(OnbordStartActivity.this, WaitingPageActivity.class);
                    startActivity(i);
                }else {
                    OnbordingActivity.init=true;
                    Intent i = new Intent(OnbordStartActivity.this, SigninActivity.class);
                    startActivity(i);
                }
            }
        });


    }


}

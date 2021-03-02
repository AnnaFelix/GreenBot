package lu.uni.bicslab.greenbot.android.ui.activity.scan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.Profile;
import lu.uni.bicslab.greenbot.android.other.ServerConnection;
import lu.uni.bicslab.greenbot.android.other.Utils;
import lu.uni.bicslab.greenbot.android.ui.activity.onbord.OnbordingActivity;

public class SigninActivity extends AppCompatActivity  {

   Button login;
   public static final int RC_BARCODE_CAPTURE = 9001;
   EditText signin_id;
   String id;
   JsonObject jsonObject;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_siginin);
        login = (Button) findViewById(R.id.tx_login);
        signin_id = (EditText) findViewById(R.id.signin_id);
        jsonObject = new JsonObject();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                id = signin_id.getText().toString();

                }catch (Exception e){
                    id = "" ;
                }
                if(id.isEmpty()||id.length()==0) {
                    Intent intent = new Intent(SigninActivity.this, SigninSelectActivity.class);
                    startActivityForResult(intent, RC_BARCODE_CAPTURE);

                }else{
                    Profile profile = new Profile();
                    profile.setSerialscanner(id);
                    profile.setLogedin(Utils.user_notloggedin);
                    Utils.saveProfile(getApplicationContext(),profile);
                    Intent intent = new Intent(SigninActivity.this, OnbordingActivity.class);
                    intent.putExtra("logintype",false);//
                    intent.putExtra("data",onJsonObjectSet(signin_id.getText().toString()).toString());//
                   // startActivityForResult(intent, RC_BARCODE_CAPTURE);
                    startActivity(intent);
                }
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("TAG", " irequestCode"+requestCode+" resultCode"+resultCode);
        if (requestCode == RC_BARCODE_CAPTURE) {
                if (data != null) {
                    String requiredValue = data.getStringExtra("barcode");
                    signin_id.setText(""+requiredValue);
                            Log.e("TAG", "Barcode read:final " +requiredValue);

                } else {
                    Log.e("TAG", "No barcode captured, intent data is null");
                }

        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private JSONObject onJsonObjectSet(String participant_id){
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("participant_id", participant_id);
            object.put("product_category_1", "null");
            object.put("product_category_2", "null");
            object.put("product_category_3", "null");
            object.put("product_category_4", "null");
            object.put("indicator_category_1", "null");
            object.put("indicator_category_2", "null");
            object.put("indicator_category_3", "null");
            object.put("indicator_category_4", "null");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}

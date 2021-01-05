package lu.uni.bicslab.greenbot.android.ui.activity.scanitem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import lu.uni.bicslab.greenbot.android.MainActivity;
import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.activity.itemmain.ItemDetailsActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.scan.SigninSelectActivity;

public class ScanSelectedItemActivity extends AppCompatActivity {

    Button login;
    public static final int RC_BARCODE_CAPTURE = 9001;
    EditText signin_id;
    String id;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_siginin);
        login = (Button) findViewById(R.id.tx_login);
        signin_id = (EditText) findViewById(R.id.signin_id);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    id = signin_id.getText().toString();
                } catch (Exception e) {
                    id = "";
                }
                if (id.isEmpty() || id.length() == 0) {
                    Intent intent = new Intent(ScanSelectedItemActivity.this, SigninSelectActivity.class);
                    startActivityForResult(intent, RC_BARCODE_CAPTURE);

                } else {

                    //goto item details activity
                    Intent intent = new Intent(getApplicationContext(), ItemDetailsActivity.class);
                    intent.putExtra("code", "2354896578459");
                    intent.putExtra("title", "Honey from Ourdaller");
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("TAG", " irequestCode" + requestCode + " resultCode" + resultCode);
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (data != null) {
                String requiredValue = data.getStringExtra("barcode");
                signin_id.setText("" + requiredValue);
                Log.e("TAG", "Barcode read:final " + requiredValue);
            } else {
                Log.e("TAG", "No barcode captured, intent data is null");
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

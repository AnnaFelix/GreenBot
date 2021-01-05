package lu.uni.bicslab.greenbot.android.ui.activity.fromprofile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.other.ServerConnection;

public class ProductsScannerActivity extends AppCompatActivity implements ServerConnection.ServerConnectionListner {


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.productscanner));
    }

    @Override
    public void onServerConnectionActionComplete(String value) {

    }
}

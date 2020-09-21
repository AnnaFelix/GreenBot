package lu.uni.bicslab.greenbot.android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.barcoderead.BarcodeGraphic;
import lu.uni.bicslab.greenbot.android.barcoderead.CameraSource;
import lu.uni.bicslab.greenbot.android.barcoderead.CameraSourcePreview;
import lu.uni.bicslab.greenbot.android.barcoderead.GraphicOverlay;
import lu.uni.bicslab.greenbot.android.barcoderead.BarcodeGraphicTracker;
import lu.uni.bicslab.greenbot.android.barcoderead.BarcodeTrackerFactory;

public class SigninActivity extends AppCompatActivity {

   Button login;
   public static final int RC_BARCODE_CAPTURE = 9001;
   EditText signin_id;

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
                Intent intent = new Intent(SigninActivity.this, SigninSelectActivity.class);
                startActivityForResult(intent, RC_BARCODE_CAPTURE);
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
}

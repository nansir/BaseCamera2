package com.sir.app.camera2;

import android.app.Activity;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sir.library.camera2.AutoFitTextureView;
import com.sir.library.camera2.Camera2Basic;
import com.sir.library.camera2.Camera2Callback;
import com.sir.library.camera2.utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhuyinan on 2018/7/27.
 * Contact by 445181052@qq.com
 */
public class MainActivity extends Activity implements Camera2Callback, View.OnClickListener {

    private AutoFitTextureView textureView;

    private Camera2Basic camera2Basic;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textureView = findViewById(R.id.texture_view);

        dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());

        camera2Basic = new Camera2Basic(this);

        camera2Basic.setCameraCallback(this);

        String id = camera2Basic.getCamerasList().get(CameraCharacteristics.LENS_FACING_BACK);

        camera2Basic.selectCamera(id);

        camera2Basic.open(CameraDevice.TEMPLATE_PREVIEW, textureView);

        findViewById(R.id.btn_take).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        camera2Basic.close();
        super.onDestroy();
    }

    @Override
    public void onCameraReady() {
        camera2Basic.setCaptureSetting(CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, CameraMetadata.COLOR_CORRECTION_ABERRATION_MODE_HIGH_QUALITY);
        camera2Basic.startPreview();
    }

    @Override
    public void onPicture(Image image) {
        camera2Basic.stopPreview();
        try {
            String filename = "image_" + dateFormat.format(new Date()) + ".jpg";
            File file = new File(getFilesDir(), filename);
            ImageUtils.saveImage(image, file);
//            Intent intent = new Intent(this, DisplayActivity.class);
//            intent.putExtra("filepath", file.getAbsolutePath());
//            startActivity(intent);
//            finish();
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    @Override
    public void onError(String message) {
        Log.e("TAG", message);
    }

    @Override
    public void onCameraDisconnected() {
        Log.e("TAG", "Camera disconnected");
    }

    @Override
    public void onClick(View v) {
        camera2Basic.takePicture();
    }
}

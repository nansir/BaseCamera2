package com.sir.library.camera2;

import android.media.Image;
/**
 * 相机回调
 * Created by zhuyinan on 2018/7/27.
 * Contact by 445181052@qq.com
 */
public interface Camera2Callback {

    void onCameraReady();

    void onPicture(Image image);

    void onError(String message);

    void onCameraDisconnected();
}
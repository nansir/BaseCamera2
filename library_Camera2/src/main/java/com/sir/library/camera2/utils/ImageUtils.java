package com.sir.library.camera2.utils;

import android.media.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by zhuyinan on 2018/7/27.
 * Contact by 445181052@qq.com
 */
public class ImageUtils {

    /**
     * 将图像保存到存储
     *
     * @param image
     * @param file
     * @return
     * @throws IOException
     */
    public static File saveImage(Image image, File file) throws IOException {
        if (file.exists()) {
            image.close();
            return null;
        }
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        FileOutputStream output = new FileOutputStream(file);
        output.write(bytes);
        image.close();
        output.close();
        return file;
    }
}

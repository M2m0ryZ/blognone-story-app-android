package com.panuwatjan.blognonestory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by benznest on 25-Sep-18.
 */

public class MyImageUtils {
    public static Bitmap resize(Bitmap bmp, int newWidth, int quality) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            byte[] byteArray = stream.toByteArray();
            bmp.recycle();

            float scale = ((float) newWidth) / bmp.getWidth();
            int newHeight = (int) (bmp.getHeight() * scale);

            Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            return Bitmap.createScaledBitmap(b, newWidth, newHeight, false);
        }catch (Exception e){

        }
        return bmp;
    }
}

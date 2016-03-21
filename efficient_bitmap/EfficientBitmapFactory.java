package com.example.ngocsonit.smartclothing.efficient_bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by ngocsonit on 08/03/2016.
 * class to process bitmap to output the best fit of usage purposes
 */
public class EfficientBitmapFactory {
    private EfficientBitmapFactory() {
        // prevent instanced
    }

    /**
     * this code referenced from android developer site : http://developer.android.com/intl/vi/training/displaying-bitmaps/load-bitmap.html
     *
     * @param options       The option of the operation
     * @param requireWidth  the desired width to be bounded
     * @param requireHeight the desired height to be bounded
     * @return the outfit bitmap sample size
     */
    private static final int calculateInSampleSize(BitmapFactory.Options options, int requireWidth, int requireHeight) {
        // get raw size the bitmap
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = -1; // the fit size to be returned
        if (height > requireHeight || width > requireWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > requireHeight
                    && (halfWidth / inSampleSize) > requireWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * @param resources
     * @param resourceId  id of resource , from drawable for example
     * @param requireSize size of View (ImageView for example)
     * @return the outfit bitmap
     */
    private static final Bitmap decodeSampleBitmapFromResource(Resources resources, int resourceId, ViewSize requireSize) {
        // instance a BitmapFactory.Options to decode bitmap
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // to get the raw dimensions
        BitmapFactory.decodeResource(resources, resourceId, options);

        // start to calculate the size
        options.inSampleSize = EfficientBitmapFactory.calculateInSampleSize(options, requireSize.getWidth(), requireSize.getHeight());

        options.inJustDecodeBounds = false; // false to has a correct operation
        return BitmapFactory.decodeResource(resources, resourceId, options); // decode and return the bitmap with the size set
    }

    /**
     * inner class ViewSize hold width and height of a View Object
     */
    public class ViewSize {
        private final int width;
        private final int height;

        public ViewSize(int width, int height) {
            this.height = height;
            this.width = width;
        }

        // getters and setters
        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }
    }

}

package br.com.desafioandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.desafioandroid.R;
import br.com.desafioandroid.model.PhotoToLoad;
import okhttp3.Cache;
import okhttp3.internal.Util;

public class ImageLoader {
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;

    public ImageLoader(Context context) {
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
    }

    final int stub_id = R.drawable.default_user;

    public void displayImage(String url, ImageView imageView) {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            queuePhoto(url, imageView);
            imageView.setImageResource(stub_id);
        }
    }

    private void queuePhoto(String url, ImageView imageView) {
        PhotoToLoad photoToLoad = new PhotoToLoad(url, imageView);
        executorService.submit(new PhotoLoader(photoToLoad));
    }

    private Bitmap getBitmap(String url) {
        File file = fileCache.getFile(url);
        Bitmap inFileBitmap = decodeFile(file);

        if (inFileBitmap!=null) {
            return  inFileBitmap;
        }

        try {
            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setInstanceFollowRedirects(true);

            InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = new FileOutputStream(file);
            CacheConfig.copyStream(inputStream, outputStream);
            outputStream.close();
            bitmap = decodeFile(file);
            return bitmap;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }

    }

    private Bitmap decodeFile(File file) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);

            final int REQ_SIZE = 70;
            int width_temp = options.outWidth, heigth_tmp = options.outHeight;
            int scale = 1;

            while (true) {
                if (width_temp/2 < REQ_SIZE || heigth_tmp/2<REQ_SIZE) {
                    break;
                }

                width_temp /= 2;
                heigth_tmp /= 2;
                scale *= 2;
            }

            BitmapFactory.Options options1 = new BitmapFactory.Options();
            options1.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(file), null, options1);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    class PhotoLoader implements Runnable {

        PhotoToLoad photoToLoad;

        PhotoLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            if (imageViewReused(photoToLoad)) {
                return;
            }

            Bitmap bmp = getBitmap(photoToLoad.url);
            memoryCache.put(photoToLoad.url, bmp);

            if (imageViewReused(photoToLoad)) {
                return;
            }

            BitmapDisplayer bitmapDisplayer = new BitmapDisplayer(bmp, photoToLoad);
            Activity activity = (Activity) photoToLoad.imageView.getContext();
            activity.runOnUiThread(bitmapDisplayer);

        }

        boolean imageViewReused(PhotoToLoad photoToLoad) {
            String tag = imageViews.get(photoToLoad.imageView);
            if (tag == null || !tag.equals(photoToLoad.url)) {
                return true;
            }
            return false;
        }

        class BitmapDisplayer implements Runnable {
            Bitmap bitmap;
            PhotoToLoad photoToLoad;
            public BitmapDisplayer(Bitmap bitmap1, PhotoToLoad photoToLoad1) {
                this.bitmap = bitmap1;
                this.photoToLoad = photoToLoad1;
            }


            @Override
            public void run() {
                if (imageViewReused(photoToLoad)) {
                    return;
                }

                if (bitmap!=null) {
                    photoToLoad.imageView.setImageBitmap(bitmap);
                } else {
                    photoToLoad.imageView.setImageResource(stub_id);
                }

            }
        }

        public void clearCache() {
            memoryCache.clearMemory();
            fileCache.clearCache();
        }

    }
}

package br.com.desafioandroid.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileCache {
    private File cacheDir;

    public FileCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(Environment.getExternalStorageDirectory(), "images");
        } else {
            cacheDir = context.getCacheDir();
        }
        if (!cacheDir.mkdirs()) {
            cacheDir.mkdirs();
        }
    }

    public File getFile(String url) {
        String filename = String.valueOf(url.hashCode());
        File file = new File(cacheDir, filename);
        return file;
    }

    public void clearCache() {
        File[] files = cacheDir.listFiles();
        if (files == null) {
            return;
        }

        for (File file: files) {
            file.delete();
        }
    }
}

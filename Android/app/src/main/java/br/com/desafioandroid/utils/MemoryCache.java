package br.com.desafioandroid.utils;

import android.graphics.Bitmap;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryCache {
    private Map<String, Bitmap> cache = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10,1.5f, true));
    private long size = 0;
    private long limit = 1000000;

    public MemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory()/4);
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public Bitmap get(String bitmapId) {
        try {
            if (!cache.containsKey(bitmapId)) {
                return null;
            }

            return cache.get(bitmapId);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String bitmapId, Bitmap bitmap) {
            try {
                if (cache.containsKey(bitmapId)) {
                    size -= getSizeInBytes(cache.get(bitmapId));
                }
                cache.put(bitmapId, bitmap);
                size += getSizeInBytes(bitmap);
                checkSize();
            } catch (Throwable e) {
                e.printStackTrace();
            }
    }

    private void checkSize() {
        if (size > limit) {
            Iterator<Map.Entry<String, Bitmap>> iterator = cache.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Bitmap> entry = iterator.next();
                size -= getSizeInBytes(entry.getValue());
                iterator.remove();
                if(size<=limit) {
                    break;
                }
            }
        }
    }

    public void clearMemory() {
        try {
            cache.clear();
            size = 0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }

        return bitmap.getRowBytes() * bitmap.getHeight();
    }

}

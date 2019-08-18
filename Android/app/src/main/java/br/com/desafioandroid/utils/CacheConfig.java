package br.com.desafioandroid.utils;

import java.io.InputStream;
import java.io.OutputStream;

public class CacheConfig {
    public static void copyStream(InputStream inputStream, OutputStream outputStream) {
        final int buffer_size = 1024;

        try {
            byte[] bytes = new byte[buffer_size];
            while (true) {
                int count = inputStream.read(bytes, 0, buffer_size);
                if (count == -1) {
                    break;
                }
                outputStream.write(bytes, 0, count);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

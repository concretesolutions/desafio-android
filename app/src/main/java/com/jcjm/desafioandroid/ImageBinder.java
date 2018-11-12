package com.jcjm.desafioandroid;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

public class ImageBinder {

    @BindingAdapter({"imageURL"})
    public static void loadImage(ImageView img, String imageUrl) {
        img.setImageURI(Uri.parse(imageUrl));
    }
}

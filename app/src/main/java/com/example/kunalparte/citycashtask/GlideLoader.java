package com.example.kunalparte.citycashtask;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideLoader {
    public static DrawableRequestBuilder<String> url(Context context){
        return Glide.with(context)
                    .fromString()
                    .fitCenter()
                    .placeholder(R.drawable.ic_photo_library_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static DrawableRequestBuilder<String> url(Activity activity){
        return Glide.with(activity)
                .fromString()
                .fitCenter()
                .placeholder(R.drawable.ic_photo_library_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static DrawableRequestBuilder<String> url(Fragment fragment){
        return Glide.with(fragment)
                .fromString()
                .fitCenter()
                .placeholder(R.drawable.ic_photo_library_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }
}

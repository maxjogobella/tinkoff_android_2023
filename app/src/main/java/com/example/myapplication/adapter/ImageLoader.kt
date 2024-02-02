package com.example.myapplication.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun load(imageView : ImageView, url : String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}
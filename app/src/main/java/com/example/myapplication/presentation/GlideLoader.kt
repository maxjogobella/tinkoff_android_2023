package com.example.myapplication.presentation

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideLoader {
    fun execute(view : View, url : String?, imageView: ImageView) {
        Glide.with(view)
            .load(url)
            .into(imageView)
    }
}
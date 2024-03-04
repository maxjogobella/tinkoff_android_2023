package com.example.myapplication.presentation.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideLoader {

    fun execute(context : Context, url : String?, imageView: ImageView) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }
}
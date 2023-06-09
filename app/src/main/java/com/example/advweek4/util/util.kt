package com.example.advweek4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.advweek4.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {


    Picasso.get().load(url).resize(400,800).centerCrop().error(R.drawable.ic_baseline_error_24).into(this, object:Callback {
        override fun onSuccess() {
            progressBar.visibility = View.GONE
        }
        override fun onError(e: Exception?) {
        }
    })
}

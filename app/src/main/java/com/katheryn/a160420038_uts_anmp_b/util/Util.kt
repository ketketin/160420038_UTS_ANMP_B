package com.katheryn.a160420038_uts_anmp_b.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.katheryn.a160420038_uts_anmp_b.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url).resize(400, 400).centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback{
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
            }
        })
}
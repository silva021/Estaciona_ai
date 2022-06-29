package com.silva021.toolkit.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.AnimRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Activity.hideKeyboard() {
    findViewById<View>(android.R.id.content).hideKeyboard()
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.startAnimation(@AnimRes animId: Int) {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            context,
            animId
        )
    )
}

fun ImageView.stopAnimation() {
    with(this) {
        if (animation != null) {
            animation.cancel()
            clearAnimation()
            gone()
        }
    }
}

fun ImageView.stopAnimation(@DrawableRes drawableId: Int) {
    with(this) {
        if (animation != null) {
            animation.cancel()
            clearAnimation()
            setImageResource(drawableId)
        }
    }
}

fun ImageView.load(@DrawableRes drawableId: Int) {
    this.setImageResource(drawableId)
}
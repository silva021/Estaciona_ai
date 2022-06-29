package com.silva021.toolkit.extension

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.silva021.toolkit.R

fun Fragment.navigate(@IdRes id: Int, bundle: Bundle = bundleOf()) {
    findNavController().navigate(id, bundle, navOptions = configNavOption())
}

fun Fragment.onBackPressed() {
    requireActivity().onBackPressed()
}

private fun configNavOption() = NavOptions.Builder().apply {
    setEnterAnim(R.anim.slide_in)
    setExitAnim(R.anim.slide_out)
    setPopEnterAnim(R.anim.slide_pop_out)
    setPopExitAnim(R.anim.slide_pop_in)
}.build()
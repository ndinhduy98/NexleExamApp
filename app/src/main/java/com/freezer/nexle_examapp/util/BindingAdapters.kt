package com.freezer.nexle_examapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingAdapters {
  companion object {
    @JvmStatic
    @BindingAdapter("rvData")
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data : T) {
      if(recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
      }
    }
  }
}
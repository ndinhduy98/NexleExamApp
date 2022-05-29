package com.freezer.nexle_examapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freezer.nexle_examapp.R
import com.freezer.nexle_examapp.data.model.Category
import com.freezer.nexle_examapp.util.BindableAdapter

class CategoryAdapter(private val listener: ItemListener<Category>) :
  RecyclerView.Adapter<CategoryAdapter.ViewHolder>(), BindableAdapter<ArrayList<Category>> {
  var categories = ArrayList<Category>()
  override fun setData(data: ArrayList<Category>) {
    categories = data
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
    return ViewHolder(view, listener)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(categories[position])
  }

  override fun getItemCount() = categories.size

  class ViewHolder(itemView: View, private val listener: ItemListener<Category>): RecyclerView.ViewHolder(itemView) {
    fun bind(category: Category) {
      val text = itemView.findViewById<TextView>(R.id.text_item_text)
      text.text = category.name
      itemView.setBackground(category)

      itemView.setOnClickListener {
        listener.onClick(category)
        it.setBackground(category)
      }
    }
    private fun View.setBackground(category: Category) {
      if(category.isSelected) {
        setBackgroundResource(R.drawable.bg_item_selected)
      } else {
        setBackgroundResource(R.drawable.bg_item_unselected)
      }
    }
  }
}
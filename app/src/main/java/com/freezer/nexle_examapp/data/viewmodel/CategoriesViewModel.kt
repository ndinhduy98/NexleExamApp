package com.freezer.nexle_examapp.data.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freezer.nexle_examapp.data.model.Category
import com.freezer.nexle_examapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.freezer.nexle_examapp.BR
import com.freezer.nexle_examapp.ui.adapter.ItemListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class CategoriesViewModel @Inject constructor(
  private val mainRepository: MainRepository,
) : Observable, ViewModel() {
  private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

  @get:Bindable
  var categories = ArrayList<Category>()
    set(value) {
      field = value
      callbacks.notifyChange(this, BR.categories)
    }

  val countSelectedCategories = MutableLiveData<Int>()

  val onItemClick = object : ItemListener<Category> {
    override fun onClick(item: Category) {
      item.isSelected = !item.isSelected
      if(!item.isSelected) {
        countSelectedCategories.postValue(countSelectedCategories.value?.minus(1))
      } else {
        countSelectedCategories.postValue(countSelectedCategories.value?.plus(1))
      }
      CoroutineScope(Dispatchers.IO).launch {
        mainRepository.insertSelectedCategory(item)
      }
    }
  }

  override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    callbacks.add(callback)
  }

  override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    callbacks.remove(callback)
  }

  init {
    CoroutineScope(Dispatchers.IO).launch {
      val selectedCategories = mainRepository.getSelectedCategories() as ArrayList<Category>
      countSelectedCategories.postValue(selectedCategories.size)
      val result = mainRepository.getCategories()
      if(result.isSuccessful) {
        categories = result.body()!!.categories

        selectedCategories.forEach { selectedCategory ->
          categories.first { category -> category.id == selectedCategory.id }.isSelected = selectedCategory.isSelected
        }
      }
    }
  }

}
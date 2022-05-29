package com.freezer.nexle_examapp.data.viewmodel

import androidx.lifecycle.ViewModel
import com.freezer.nexle_examapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository,
): ViewModel() {

}
package com.freezer.nexle_examapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freezer.nexle_examapp.data.model.Category
import com.freezer.nexle_examapp.data.repository.MainRepository
import com.freezer.nexle_examapp.data.viewmodel.CategoriesViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import org.junit.Assert.*

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class CategoriesViewModelTest {
  private lateinit var categoriesViewModel: CategoriesViewModel

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @Inject
  lateinit var mainRepository: MainRepository

  @Before
  fun init() {
    hiltRule.inject()
    categoriesViewModel = CategoriesViewModel(mainRepository)
  }

  @Test
  fun countSelectedItems() {
    val exam1 = Category("1", "A", isSelected = true)
    val exam2 = Category("2", "B", isSelected = false)
    categoriesViewModel.categories.add(exam1)
    categoriesViewModel.selectedCategories.add(exam1)
    categoriesViewModel.categories.add(exam2)

    categoriesViewModel.onItemClick.onClick(exam2)
    assertEquals(2, categoriesViewModel.countSelectedCategories.value)
  }
}
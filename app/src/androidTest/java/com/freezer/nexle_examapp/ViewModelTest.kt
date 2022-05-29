package com.freezer.nexle_examapp

import android.content.Context
import android.text.Editable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.freezer.nexle_examapp.data.repository.MainRepository
import com.freezer.nexle_examapp.data.viewmodel.SignUpViewModel
import com.freezer.nexle_examapp.di.module.AppPreference
import com.freezer.nexle_examapp.util.Password
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ViewModelTest {
  private lateinit var signUpViewModel: SignUpViewModel

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @Inject
  lateinit var appPreference: AppPreference

  @Inject
  lateinit var mainRepository: MainRepository

  @Before
  fun init() {
    hiltRule.inject()
    signUpViewModel = SignUpViewModel(mainRepository, appPreference)
  }

  @Test
  fun passwordValidator_tooShort() {
    val editable = Editable.Factory().newEditable("asdas")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.TOO_SHORT, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun passwordValidator_weak() {
    val editable = Editable.Factory().newEditable("abfasdasa")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.WEAK, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun passwordValidator_fairSymbol() {
    val editable = Editable.Factory().newEditable("abfasdasa@")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.FAIR, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun passwordValidator_fairUpperCase() {
    val editable = Editable.Factory().newEditable("abfasdaHsa")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.FAIR, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun passwordValidator_fairNumber() {
    val editable = Editable.Factory().newEditable("abfasd2asa")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.FAIR, signUpViewModel.passwordStrength.value)
  }
}
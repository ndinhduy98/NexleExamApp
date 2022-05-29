package com.freezer.nexle_examapp

import android.text.Editable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.freezer.nexle_examapp.data.repository.MainRepository
import com.freezer.nexle_examapp.data.viewmodel.SignUpViewModel
import com.freezer.nexle_examapp.di.module.AppPreference
import com.freezer.nexle_examapp.util.Password
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import org.junit.Assert.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class SignUpViewModelTest {
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

  @Test
  fun passwordValidator_goodNumberSymbol() {
    val editable = Editable.Factory().newEditable("abfasd2as@")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.GOOD, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun passwordValidator_goodNUmberUpperCase() {
    val editable = Editable.Factory().newEditable("abfHsd2as")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.GOOD, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun passwordValidator_strong() {
    val editable = Editable.Factory().newEditable("asdH@ss24")
    signUpViewModel.validatePassword(editable)
    assertEquals(Password.Strength.STRONG, signUpViewModel.passwordStrength.value)
  }

  @Test
  fun emailValidator_correct() {
    val editable = Editable.Factory().newEditable("test@gmail.com")
    signUpViewModel.validateEmail(editable)
    assertEquals(true, signUpViewModel.isEmailValid.value)
  }

  @Test
  fun emailValidator_failed() {
    val editable = Editable.Factory().newEditable("testgmail.com")
    signUpViewModel.validateEmail(editable)
    assertEquals(false, signUpViewModel.isEmailValid.value)
  }
}
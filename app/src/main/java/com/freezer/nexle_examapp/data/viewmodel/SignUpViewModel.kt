package com.freezer.nexle_examapp.data.viewmodel

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freezer.nexle_examapp.data.repository.MainRepository
import com.freezer.nexle_examapp.di.module.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val appPreference: AppPreference
): ViewModel() {
  val passwordStrength = MutableLiveData(-2)
  val isEmailValid = MutableLiveData<Boolean>()
  val isEmailEmpty = MutableLiveData(false)
  val isPasswordEmpty = MutableLiveData(false)
  val isSignUpSuccessful = MutableLiveData<Boolean>()

  var email: String = ""
  var password: String = ""

  fun validateEmail(editable: Editable) {
    isEmailEmpty.postValue(false)
    val emailText = editable.toString()
    email = emailText

    val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    if(emailText.matches(emailRegex))
      isEmailValid.postValue(true)
    else
      isEmailValid.postValue(false)
  }

  fun validatePassword(editable: Editable) {
    isPasswordEmpty.postValue(false)
    var passwordStrengthLevel = 0
    val passwordText = editable.toString()
    password = passwordText

    val lengthRegex = Regex(".{6,18}.")
    val letterRegex = Regex("(.*[a-z].*)(.*[A-Z].*)")
    val numericRegex = Regex("(.*\\d.*)")
    val symbolRegex = Regex("(.*\\W.*)")

    if(!passwordText.matches(lengthRegex))
      return passwordStrength.postValue(-1)
    if(passwordText.matches(letterRegex))
      passwordStrengthLevel += 1
    if(passwordText.matches(numericRegex))
      passwordStrengthLevel += 1
    if(passwordText.matches(symbolRegex))
      passwordStrengthLevel += 1

    passwordStrength.postValue(passwordStrengthLevel)
  }

  fun onSignUpClick(view: View) {
    isEmailEmpty.postValue(email.isEmpty())
    isPasswordEmpty.postValue(password.isEmpty())

    if(passwordStrength.value!! >= 0 && isEmailValid.value == true) {
      // Sign Up
      CoroutineScope(Dispatchers.IO).launch {
        val response = mainRepository.signUp(email, password)
        if(response.isSuccessful) {
          isSignUpSuccessful.postValue(true)
          appPreference.setToken(response.body()!!.token)
          appPreference.setRefreshToken(response.body()!!.refreshToken)
        } else {
          isSignUpSuccessful.postValue(false)
        }
      }
    }
  }
}
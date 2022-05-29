package com.freezer.nexle_examapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.freezer.nexle_examapp.R
import com.freezer.nexle_examapp.data.viewmodel.SignUpViewModel
import com.freezer.nexle_examapp.databinding.FragmentSignUpBinding
import com.freezer.nexle_examapp.util.Password
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
  private var _binding: FragmentSignUpBinding? = null

  private val binding get() = _binding!!

  private val signUpViewModel: SignUpViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSignUpBinding.inflate(inflater, container, false)

    binding.viewModel = signUpViewModel
    binding.lifecycleOwner = this

    setUpObserver()
    setUpUi()
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun setUpObserver() {
    signUpViewModel.passwordStrength.observe(viewLifecycleOwner) { passwordStrength ->
      var color: Int = ContextCompat.getColor(requireContext(), R.color.password_too_short)
      var text = ""
      var progress = 0
      when(passwordStrength) {
        Password.Strength.TOO_SHORT -> {
          color = ContextCompat.getColor(requireContext(), R.color.password_too_short)
          progress = 0
          text = getString(R.string.text_password_too_short)
        }
        Password.Strength.WEAK -> {
          color = ContextCompat.getColor(requireContext(), R.color.password_weak)
          progress = 25
          text = getString(R.string.text_password_weak)
        }
        Password.Strength.FAIR -> {
          color = ContextCompat.getColor(requireContext(), R.color.password_fair)
          progress = 50
          text = getString(R.string.text_password_fair)
        }
        Password.Strength.GOOD -> {
          color = ContextCompat.getColor(requireContext(), R.color.password_good)
          progress = 75
          text = getString(R.string.text_password_good)
        }
        Password.Strength.STRONG -> {
          color = ContextCompat.getColor(requireContext(), R.color.password_strong)
          progress = 100
          text = getString(R.string.text_password_strong)
        }
      }
      indicatePasswordStrength(color, progress, text)
    }

    signUpViewModel.isEmailValid.observe(viewLifecycleOwner) { isValid ->
      binding.inputLayoutEmail.isErrorEnabled = !isValid
    }

    signUpViewModel.isSignUpSuccessful.observe(viewLifecycleOwner) { isSuccessful ->
      if(isSuccessful) {
        NavHostFragment.findNavController(this).navigate(R.id.categoriesFragment)
      }
    }
  }

  private fun setUpUi() {
    binding.buttonSignUp.setOnClickListener { signUpViewModel.onSignUpClick(it) }
    // Disable Seekbar
    binding.seekbarPassword.setOnTouchListener { _, _ ->  true }
  }

  private fun indicatePasswordStrength(color: Int, progress: Int, text: String) {
    binding.seekbarPassword.progress = progress
    binding.seekbarPassword.progressDrawable.setTint(color)
    binding.textPasswordStrongIndicator.text = text
    binding.textPasswordStrongIndicator.setTextColor(color)
  }
}
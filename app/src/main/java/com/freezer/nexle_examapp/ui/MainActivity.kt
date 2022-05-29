package com.freezer.nexle_examapp.ui

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.freezer.nexle_examapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private var _binding: ActivityMainBinding? = null

  private val binding get() = _binding!!

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  private fun hideStatusBar() {
    val decorView: View = window.decorView
    val uiOptions: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
    decorView.setSystemUiVisibility(uiOptions)
    val actionBar: ActionBar? = actionBar
    actionBar?.hide()
  }
}
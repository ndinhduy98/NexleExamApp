package com.freezer.nexle_examapp.data.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
  @field:SerializedName("_id")
  val id: String,
  val firstName: String,
  val lastName: String,
  val displayName: String,
  val token: String,
  val refreshToken: String
)
package com.kotlinreactanotherbridge.reactnative.withdraw

import kotlinx.serialization.Serializable

@Serializable
data class WithdrawItem(
  val year: Int,
  val someBoolean: Boolean,
  val id: String,
  val items: List<WithdrawSomeItem>
)

@Serializable
data class WithdrawSomeItem(
  val title: String,
  val value: String
)
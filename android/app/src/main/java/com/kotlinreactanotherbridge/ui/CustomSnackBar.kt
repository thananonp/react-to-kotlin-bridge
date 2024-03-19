package com.kotlinreactanotherbridge.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun CustomSnackBar(
  @DrawableRes drawableRes: Int,
  message: String,
  isRtl: Boolean = true,
  containerColor: Color = Color.Black
) {
  Snackbar(containerColor = containerColor) {
    CompositionLocalProvider(
      LocalLayoutDirection provides if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
      Row() {
        Icon(
          painterResource(id = drawableRes), contentDescription = null
        )
        Text(message)
      }
    }
  }
}
package com.kotlinreactanotherbridge.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


val onboardPage = listOf(
  "Welcome to Jitta Wealth",
  "Jitta Ranking",
  "Global ETF",
  "Thematic",
  "Jitta Money",
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardCarousel(onClickLogin: () -> Unit) {
  val state = rememberLazyListState()

  val visibleIndex by remember {
    derivedStateOf {
      state.firstVisibleItemIndex
    }
  }

  Column {
    Row {
      onboardPage.forEachIndexed { index, s ->
        val backgroundColor = if (visibleIndex == index) Color.Green else Color.Gray
        Box(
          modifier = Modifier
            .padding(8.dp)
            .weight(1f)
            .height(3.dp)
            .clip(RectangleShape)
            .background(backgroundColor)

        )
      }
    }

    LazyRow(
      Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .weight(1f),
      state = state,
      flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    ) {
      items(onboardPage) {
        Column(Modifier.fillParentMaxWidth()) {
          Text(text = it, Modifier.fillMaxWidth())
          Text(text = "Page ${visibleIndex + 1} of ${onboardPage.size}")
        }
      }
    }

    Button(onClick = { onClickLogin() }) {
      Text("Log in")
    }
  }
}

@Preview(device = "id:pixel_5", showBackground = true)
@Composable
fun OnboardCarouselPreview() {
  OnboardCarousel({})
}
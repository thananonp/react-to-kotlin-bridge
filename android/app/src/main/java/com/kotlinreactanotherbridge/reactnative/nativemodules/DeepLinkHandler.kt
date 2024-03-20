package com.kotlinreactanotherbridge.reactnative.nativemodules

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.kotlinreactanotherbridge.MainActivity

class DeepLinkHandler(private val reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  override fun getName(): String = "DeepLinkHandler"

  @ReactMethod
  fun openDeepLink(link: String) {
    val openMainActivity = Intent(reactContext, MainActivity::class.java)
    openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    reactContext.currentActivity?.startActivityIfNeeded(openMainActivity, 0)
  }
}
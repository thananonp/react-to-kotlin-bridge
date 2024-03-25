package com.kotlinreactanotherbridge.reactnative.withdraw

import android.os.Bundle
import android.view.KeyEvent
import com.facebook.hermes.reactexecutor.HermesExecutorFactory
import com.facebook.react.PackageList
import com.facebook.react.ReactActivity
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.kotlinreactanotherbridge.BuildConfig
import com.kotlinreactanotherbridge.reactnative.MyPackage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WithdrawActivity : ReactActivity(), DefaultHardwareBackBtnHandler {
  private lateinit var reactRootView: ReactRootView
  private lateinit var reactInstanceManager: ReactInstanceManager
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(null)
    reactRootView = ReactRootView(this)
    val packages: List<ReactPackage> = PackageList(application).packages.apply {
      add(MyPackage())
    }
    reactInstanceManager = ReactInstanceManager.builder()
      .setApplication(application)
      .setCurrentActivity(this)
      .setBundleAssetName("index.android.bundle")
      .setJSMainModulePath("index")
      .addPackages(packages)
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .setJavaScriptExecutorFactory(HermesExecutorFactory())
      .build()
    // The string here (e.g. "MyReactNativeApp") has to match
    // the string in AppRegistry.registerComponent() in index.js

    val testJson = WithdrawItem(
      year = 2024,
      someBoolean = false,
      id = "ASDFASRWER",
      items = listOf(
        WithdrawSomeItem("Hello", "World"),
        WithdrawSomeItem("สวัสดี", "โลก")
      )
    )

    val initialBundle = Bundle().apply {
      putString("testString", "Hello")
      putInt("testInt", 123)
      putString("testJson", Json.encodeToString(testJson))
    }

    reactRootView.startReactApplication(reactInstanceManager, "WithdrawStack", initialBundle)
    setContentView(reactRootView)
  }

  override fun onPause() {
    super.onPause()
    reactInstanceManager.onHostPause(this)
  }

  override fun onResume() {
    super.onResume()
    reactInstanceManager.onHostResume(this, this)
  }

  override fun onDestroy() {
    super.onDestroy()
    reactInstanceManager.onHostDestroy(this)
    reactRootView.unmountReactApplication()
  }

  override fun onBackPressed() {
    reactInstanceManager.onBackPressed()
  }

  override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
    if (keyCode == KeyEvent.KEYCODE_MENU) {
      reactInstanceManager.showDevOptionsDialog()
      return true
    }
    return super.onKeyUp(keyCode, event)
  }
}
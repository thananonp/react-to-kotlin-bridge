package com.kotlinreactanotherbridge.reactnative.withdraw

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.facebook.hermes.reactexecutor.HermesExecutorFactory
import com.facebook.react.PackageList
import com.facebook.react.ReactActivity
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.BaseJavaModule
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import com.kotlinreactanotherbridge.BuildConfig
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WithdrawNativeModule(
  private val delegate: WithdrawActivityDelegate
) : BaseJavaModule(), WithdrawActivityDelegate {

  @ReactMethod
  override fun onAppear() {
    delegate.onAppear()
  }

  @ReactMethod
  override fun onWithdraw(amount: Double) {
    delegate.onWithdraw(amount)
  }

  @ReactMethod
  override fun onFinish() {
    delegate.onFinish()
  }

  override fun getName(): String {
    return "WithdrawNativeModule"
  }
}

interface WithdrawActivityDelegate {
  fun onAppear()
  fun onWithdraw(amount: Double)
  fun onFinish()
}

class WithdrawActivityPackage(
  private val withdrawNativeModule: WithdrawNativeModule
) : ReactPackage {

  override fun createNativeModules(p0: ReactApplicationContext): MutableList<NativeModule> {
    return mutableListOf(withdrawNativeModule)
  }

  override fun createViewManagers(p0: ReactApplicationContext): MutableList<ViewManager<View, ReactShadowNode<*>>> {
    return mutableListOf()
  }
}

class WithdrawActivity : ReactActivity(), DefaultHardwareBackBtnHandler, WithdrawActivityDelegate {
  private lateinit var reactRootView: ReactRootView
  private lateinit var reactInstanceManager: ReactInstanceManager
  private val withdrawNativeModule = WithdrawNativeModule(this)
  private val TAG = this::class.java.name

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(null)
    reactRootView = ReactRootView(this)
    val packages: List<ReactPackage> = PackageList(application).packages.apply {
      add(WithdrawActivityPackage(withdrawNativeModule))
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

    val testJson = Json.encodeToString(
      WithdrawInitialItem(
        year = 2024,
        someBoolean = false,
        id = "ASDFASRWER",
        items = listOf(
          WithdrawSomeItem("Hello", "World"),
          WithdrawSomeItem("สวัสดี", "โลก")
        )
      )
    )

    val initialBundle = Bundle().apply {
      putString("testJson", testJson)
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

  override fun onAppear() {
    Log.d(TAG, "onAppear: ")
  }

  override fun onWithdraw(amount: Double) {
    Log.d(TAG, "onWithdraw: ")
  }

  override fun onFinish() {
    Log.d(TAG, "onFinish: ")
  }
}
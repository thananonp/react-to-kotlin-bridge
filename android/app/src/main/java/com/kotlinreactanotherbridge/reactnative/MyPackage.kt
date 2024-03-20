package com.kotlinreactanotherbridge.reactnative

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import com.kotlinreactanotherbridge.reactnative.nativemodules.DeepLinkHandler

class MyPackage: ReactPackage {
  override fun createNativeModules(p0: ReactApplicationContext): MutableList<NativeModule> {
    return mutableListOf(
      DeepLinkHandler(p0)
    )
  }

  override fun createViewManagers(p0: ReactApplicationContext): MutableList<ViewManager<View, ReactShadowNode<*>>> {
    return mutableListOf()
  }

}
package com.example.ares.buttonnavigation.Utils

import android.support.test.espresso.IdlingResource

class FetcherIdlingResouce : IdlingResource ,FetcherListener {
        var idle = true
        var resourceCallback: IdlingResource.ResourceCallback?= null

    override fun getName(): String {
        return FetcherIdlingResouce::class.java.simpleName
    }

    override fun isIdleNow() = idle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
        }

    override fun doneFetching() {
        idle = true
        resourceCallback?.onTransitionToIdle()

    }

    override fun beginFetching() {
        idle = false
    }


}


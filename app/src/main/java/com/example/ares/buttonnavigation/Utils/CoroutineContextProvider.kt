package com.example.ares.buttonnavigation.Utils

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutineContextProvider{
        open val main:CoroutineContext by lazy { UI }
}
package com.example.ares.buttonnavigation.Utils

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider(){
    override val main: CoroutineContext = Unconfined

}
package com.codeworld.composeui.ui.effect_handlers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EffectHandlersViewModel: ViewModel() {

    private var incrementedValue = MutableStateFlow(0)
    val _value = incrementedValue.asStateFlow()

    fun incrementValue() {
        incrementedValue.value += 1
    }


}
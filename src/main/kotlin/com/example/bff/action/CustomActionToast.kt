package com.example.bff.action

import br.com.zup.beagle.annotation.RegisterAction
import br.com.zup.beagle.widget.action.Action

@RegisterAction
data class CustomAction(
    val msg: String? = null
) : Action
package com.example.bff.widget

import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.Widget

@RegisterWidget
data class CustomWidget(
    val text1:String? = null,
    val text2:String? = null
) : Widget()
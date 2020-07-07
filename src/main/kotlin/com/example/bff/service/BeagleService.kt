package com.example.bff.service

import com.example.bff.builder.ScreenBeagleBuilder
import org.springframework.stereotype.Service

@Service
class BeagleService {
    fun createBeagleScreen() = ScreenBeagleBuilder()
    fun createTabView() = ScreenBeagleBuilder()
}
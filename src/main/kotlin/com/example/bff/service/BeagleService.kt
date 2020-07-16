package com.example.bff.service

import com.example.bff.builder.GenreBuilder
import com.example.bff.builder.ScreenBeagleBuilder
import com.example.bff.builder.ScreenContextBuilder
import com.example.bff.builder.SingleComponentBuilder
import org.springframework.stereotype.Service

@Service
class BeagleService {
    fun createBeagleScreen() = ScreenBeagleBuilder()
    fun createTabView() = ScreenBeagleBuilder()
    fun createServerDrivenComponent() = SingleComponentBuilder().createButton()
    fun createCpfTest() = ScreenContextBuilder
    fun createGenreScreen() = GenreBuilder().createButton()
}
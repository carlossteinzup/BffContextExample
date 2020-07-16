package com.example.bff.controller

import com.example.bff.service.BeagleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScreenController(
        private val screenBeagleService: BeagleService
) {
    @GetMapping("/screen")
    fun getFirstScreen() = screenBeagleService.createBeagleScreen()

  @GetMapping("/tabview")
  fun getTabView() = screenBeagleService.createTabView()

  @GetMapping("/serverDrivenComponent")
  fun getServerDrivenComponent() = screenBeagleService.createServerDrivenComponent()

  @GetMapping("/cpf_test")
  fun getCpfTestComponent() = screenBeagleService.createCpfTest()

  @GetMapping("/genreScreen")
  fun getGenreScreenComponent() = screenBeagleService.createGenreScreen()
}
package com.example.bff.builder

import br.com.zup.beagle.annotation.BeaglePreview
import br.com.zup.beagle.core.*
import br.com.zup.beagle.ext.*
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Text
import com.example.bff.action.CustomAction
import com.example.bff.widget.CustomWidget

@BeaglePreview
fun buildPreview() = ScreenBeagleBuilder()

class ScreenBeagleBuilder : ScreenBuilder {
  override fun build() = Screen(
      child = Container(
          children = listOf(
              Text(
                  text = "Hello Beagle!"
              ).applyStyle(
                  Style(margin = EdgeValue(top = 16.unitReal()),
                      flex = Flex(alignSelf = AlignSelf.CENTER)
                  )
              ),
              Container(
                  listOf(
                      Text("Sou um texto Server-Driven"),
                      Text("Sou outro texto Server-Driven")
                  )
              ).applyStyle(
                  Style(backgroundColor = "#ff8100",
                      cornerRadius = CornerRadius(25.0),
                      size = Size(width = 70.unitPercent(), height = 80.unitReal()),
                      margin = EdgeValue(all = 20.unitReal()),
                      padding = EdgeValue(all = 15.unitReal()),
                      position = EdgeValue(0.unitReal()),
                      flex = Flex(FlexDirection.ROW),
                      positionType = PositionType.RELATIVE,
                      display = Display.FLEX)
              ).applyAccessibility(accessibility = Accessibility(
                  accessible = true,
                  accessibilityLabel = "Sou acessível")
              ).setId("SouUmWidget")
              ,
              CustomWidget(
                      text1 = "Sou um texto Server-Driven",
                      text2 = "Sou outro texto Server-Driven"
              ).applyStyle(
                  Style(backgroundColor = "#ff8100",
                      cornerRadius = CornerRadius(25.0),
                      size = Size(width = 70.unitPercent(), height = 80.unitReal()),
                      margin = EdgeValue(all = 20.unitReal()),
                      padding = EdgeValue(all = 15.unitReal()),
                      position = EdgeValue(0.unitReal()),
                      flex = Flex(FlexDirection.ROW),
                      positionType = PositionType.RELATIVE,
                      display = Display.FLEX)
              ).applyAccessibility(accessibility = Accessibility(
                  accessible = true,
                  accessibilityLabel = "Sou acessível")
              ).setId("SouUmWidget")
              ,
              Button(
                  text = "Faz um Toast",
                  styleId = "DesignSystem.MeuEstiloDeBotãoNativo",
                  onPress = listOf(CustomAction("Eu sou um Toast"))
              ).applyStyle(
                  Style(margin = EdgeValue(top = 16.unitReal()),
                      flex = Flex(alignSelf = AlignSelf.CENTER)
                  )
              ),
              Button(
                  text = "Faz um Alert",
                  styleId = "DesignSystem.MeuEstiloDeBotãoNativo",
                  onPress = listOf(
                      Alert(
                          title = "Eu sou o título de um alerta",
                          message = "Eu sou a mensagem de um Alerta",
                          labelOk = "Eu sou um Ok customizado",
                          onPressOk = CustomAction("Eu sou um Toast mas posso ser qualquer ação")
                      )
                  )

              ).applyStyle(
                  Style(margin = EdgeValue(top = 16.unitReal()),
                      flex = Flex(alignSelf = AlignSelf.CENTER)
                  )
              ),
              Text(
                  text = "Beagle is a cross-platform framework which provides usage of the " +
                      "Server-Driven UI concept, natively in iOS, Android and Web applications. " +
                      "By using Beagle, your team could easily change application's layout and" +
                      " data by just changing backend code."
              ).applyStyle(
                  Style(margin = EdgeValue(
                      left = 16.unitReal(),
                      right = 16.unitReal(),
                      top = 20.unitReal()
                  )
                  )
              )
          )
      )
  )
}
package com.example.bff.builder

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.*
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Text
import br.com.zup.beagle.widget.ui.TextInput

data class Address(val data: Data)

data class Data(
    val zip: String,
    val street: String,
    val number: String,
    val neighborhood: String,
    val city: String,
    val state: String,
    val complement: String
)

object ScreenContextBuilder : ScreenBuilder {
  var styleMargin = Style(
      margin = EdgeValue(
          top = 10.unitReal(),
          left = 25.unitReal(),
          right = 25.unitReal()
      )
  )

  override fun build() = Screen(
      navigationBar = NavigationBar(
          title = "Beagle Context",
          showBackButton = true
      ),
      child = Container(
          children = listOf(
              ScrollView(
                  children = listOf(
                      Text(
                          text = "Fill the form",
                          styleId = "DesignSystem.Text.helloWord"
                      ).applyStyle(
                          Style(
                              margin = EdgeValue(top = 20.unitReal(), bottom = 20.unitReal()),
                              flex = Flex(
                                  alignSelf = AlignSelf.CENTER
                              )
                          )
                      ),
                      createZip(),
                      createTextInput(),
                      createButton()
                  ),
                  context = ContextData(
                      id = "address",
                      value = Address(
                          data = Data(
                              zip = "",
                              street = "",
                              number = "",
                              neighborhood = "",
                              city = "",
                              state = "",
                              complement = ""
                          )
                      )
                  )
              )
          )
      ).applyFlex(Flex(grow = 1.0))
  )

  private fun createTextInput() = Container(
      children = listOf(
          createTextInput(
              textInputPlaceholder = "Street",
              textInputValue = "@{address.data.street}",
              contextPath = "data.street"
          ),
          createTextInput(
              textInputPlaceholder = "Number",
              textInputValue = "@{address.data.number}",
              contextPath = "data.number",
              type = TextInputType.NUMBER
          ),
          createTextInput(
              textInputPlaceholder = "Neighborhood",
              textInputValue = "@{address.data.neighborhood}",
              contextPath = "data.neighborhood"
          ),
          createTextInput(
              textInputPlaceholder = "City",
              textInputValue = "@{address.data.city}",
              contextPath = "data.city"
          ),
          createTextInput(
              textInputPlaceholder = "State",
              textInputValue = "@{address.data.state}",
              contextPath = "data.state"
          ),
          createTextInput(
              textInputPlaceholder = "Complement",
              textInputValue = "@{address.data.complement}",
              contextPath = "data.complement"
          )
      )
  )

  private fun createZip() = TextInput(
      placeholder = "ZIP",
      value = "@{address.data.zip}",
      styleId = "DesignSystem.TextInput.Style.Bff",
      type = TextInputType.NUMBER,
      onChange = listOf(
          SetContext(
              contextId = "address",
              path = "data.zip",
              value = "@{onChange.value}"
          )
      ),
      onBlur = listOf(
          SendRequest(
              url = "https://viacep.com.br/ws/@{onBlur.value}/json",
              method = RequestActionMethod.GET,
              onSuccess = listOf(
                  SetContext(
                      contextId = "address",
                      path = "data",
                      value = Data(
                          zip = "@{onBlur.value}",
                          street = "@{onSuccess.data.logradouro}",
                          number = "@{address.data.number}",
                          neighborhood = "@{onSuccess.data.bairro}",
                          city = "@{onSuccess.data.localidade}",
                          state = "@{onSuccess.data.uf}",
                          complement = "@{address.data.complement}"
                      )
                  )
              )
          )
      )
  ).applyStyle(styleMargin)


  private fun createButton() = Button(
      text = "Enviar",
      styleId = "DesignSystem.Button.Context",
      onPress = listOf(
          Confirm(
              title = "Address form!",
              message = "The data is correct?\n" +
                  "Street: @{address.data.street}\n" +
                  "Number: @{address.data.number}\n" +
                  "Neighborhood: @{address.data.neighborhood}\n" +
                  "City: @{address.data.city}\n" +
                  "State: @{address.data.state}\n" +
                  "Complement: @{address.data.complement}",
              onPressOk = Alert(
                  title = "Address form",
                  message = "The form was successfully!",
                  onPressOk = SetContext(
                      contextId = "address",
                      path = "data",
                      value =
                      Data(
                          zip = "",
                          street = "",
                          number = "",
                          neighborhood = "",
                          city = "",
                          state = "",
                          complement = ""
                      )
                  )
              )
          )
      )
  ).applyStyle(
      Style(
          backgroundColor = "#808080",
          cornerRadius = CornerRadius(8.0),
          size = Size(width = 50.unitPercent()),
          margin = EdgeValue(
              top = 30.unitReal()
          ),
          flex = Flex(
              alignSelf = AlignSelf.CENTER
          )
      )
  )

  private fun createTextInput(
      textInputPlaceholder: String,
      textInputValue: String,
      contextPath: String,
      type: TextInputType? = null
  ): TextInput = TextInput(
      placeholder = textInputPlaceholder,
      value = textInputValue,
      type = type,
      styleId = "DesignSystem.TextInput.Style.Bff",
      onChange = listOf(
          SetContext(
              contextId = "address",
              path = contextPath,
              value = "@{onChange.value}"
          )
      )
  ).applyStyle(styleMargin)
}


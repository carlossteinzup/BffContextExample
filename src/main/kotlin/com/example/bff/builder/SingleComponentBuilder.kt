package com.example.bff.builder

import br.com.zup.beagle.widget.action.RequestActionMethod
import br.com.zup.beagle.widget.action.SendRequest
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.Bind
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Text

class SingleComponentBuilder() {

  fun createButton(): Container {
    return Container(
        context = ContextData(
            id = "myContext",
            value = Currency(
                    eur = "Teste",
                    brl = "Teste2",
                    cad = "12445",
                    usd = "789"
            )
        ),
        children = listOf(
            Text("Currency Table in USD"),
            Button(
                text = "Click to get the currency Table",
                onPress = listOf(
                    SendRequest(
                        url = "https://api.exchangeratesapi.io/latest?base=USD",
                        method = RequestActionMethod.GET,
                        onSuccess = listOf(
                            SetContext(
                                contextId = "myContext",
                                value = Currency(
                                    eur = "@{onSuccess.data.rates.EUR}",
                                    brl = "@{onSuccess.data.rates.BRL}",
                                    cad = "@{onSuccess.data.rates.CAD}",
                                    usd = "@{onSuccess.data.rates.USD}"
                                )
                            )
                        )
                    )
                )
            ),

            Text(
                text = expressionOf("EUR "+"@{myContext.eur}")
            ),
            Text(
                text = "BRL: " + "@{myContext.brl}"
            ),
            Text(
                text = "CAD: " + "@{myContext.cad}"
            ),
            Text(
                text = "USD: " + "@{myContext.usd}"
            ),
            Text(
                text = "Qualquer coisa"
            )
        )
    )
  }
}

data class Currency(val eur: String, val usd: String, val brl: String, val cad: String) {}

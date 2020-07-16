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

class GenreBuilder() {

  fun createButton(): Container {
    return Container(
        context = ContextData(
            id = "myContext",
            value = Genres()
        ),
        children = listOf(
            Text("Currency Table in USD"),
            Button(
                styleId = "TestButton",
                text = "Click to get the currency Table",
                onPress = listOf(
                    SendRequest(
                        url = "https://api.themoviedb.org/3/genre/movie/list?api_key=385801b00919de93e960028b6ca5e4cd&language=en-US",
                        method = RequestActionMethod.GET,
                        onSuccess = listOf(
                            SetContext(
                                contextId = "myContext",
                                value = "@{onSuccess.data}"
                            )
                        )
                    )
                )
            ),
            Text(
                text = expressionOf("@{myContext.genres[0].name}")
            )
        )
    )
  }
}

data class Genre(val id:Long, val name:String)
data class Genres(val genres: List<Genre>?=null)


data class Result(val result: Bind<Genres>)

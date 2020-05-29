package components

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.input
import react.dom.p
import kotlin.browser.window

data class SessionState(
        val name: String? = null,
        var error: String? = null) : RState

data class CreateSessionRequest(val name: String)

class CreateSessionComponent : RComponent<RProps, SessionState>() {

    private fun handleNameChange(event: Event) {
        val name = (event.target as HTMLInputElement).value;
        this.setState(SessionState(name))
    }

    private fun createSessionAsync() = GlobalScope.async {
        state.name?.let { name ->
            window.fetch("http://localhost:8080/api/sessions", RequestInit(
                    method = "POST",
                    body = CreateSessionRequest(name)
            ))
                    .await()
                    .json()
                    .then { Response.redirect("/join") }
                    .catch { error -> setState({ it.copy(error = error.toString())}) }
        } ?: setState(SessionState(error = "Missing name"))
    }


    override fun RBuilder.render() {
        p {
            +"Error: ${state.error}"
        }
        input(type = InputType.text, name = "name") {
            attrs {
                onChangeFunction = this@CreateSessionComponent::handleNameChange
            }
        }
        input(type = InputType.button) {
            attrs {
                onClickFunction = {
                    createSessionAsync();
                }
                value = "Create session"
            }
        }
    }

}

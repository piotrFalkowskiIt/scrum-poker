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
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.input
import react.dom.p
import react.router.dom.redirect
import kotlin.browser.window
import kotlin.js.json

data class CreateSessionRequest(val name: String)
data class CreateSessionResponse(val id: String, val created: Number)

data class SessionState(
        val name: String? = null,
        var error: String? = null,
        val redirect: Boolean = false,
        val response: CreateSessionResponse? = null
) : RState


class CreateSessionComponent : RComponent<RProps, SessionState>() {

    private fun handleNameChange(event: Event) {
        val name = (event.target as HTMLInputElement).value
        this.setState(SessionState(name))
    }

    private fun createSessionAsync() = GlobalScope.async {
        state.name?.let { name ->
            window.fetch("http://localhost:8080/api/session", RequestInit(
                    method = "POST",
                    body = JSON.stringify(CreateSessionRequest(name)),
                    headers = json("Content-Type" to "application/json;charset=UTF-8")
            ))
                    .await()
                    .json()
                    .then { response -> setState(SessionState(redirect = true, response = response.unsafeCast<CreateSessionResponse>())) }
                    .catch { error -> setState({ SessionState(error = error.toString()) }) }
        } ?: setState(SessionState(error = "Missing name"))
    }


    override fun RBuilder.render() {
        if (state.redirect) {
            redirect(to = "/join/${state.response?.id}")
        } else {
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
                        createSessionAsync()
                    }
                    value = "Create session"
                }
            }
        }
    }

}

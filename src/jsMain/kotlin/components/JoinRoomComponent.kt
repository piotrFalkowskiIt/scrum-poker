package components

import kotlinext.js.js
import kotlinx.html.FormMethod
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

data class Session(val id: String, val name: String)

class JoinRoomState(
        val sessions: List<Session> = emptyList()
) : RState


class JoinRoomComponent : RComponent<RProps, JoinRoomState>() {

    private val idForm = "joinRoomForm";

    override fun componentDidMount() {
        setState(JoinRoomState(sessions = listOf(
                Session(id="122", name="Wolverine"),
                Session(id="123", name="Kodex")
        )))
    }

    override fun RBuilder.render() {
        div {
            attrs.style = js {
                display = "flex"
                flexDirection = "column"
                border = "1px solid #9c0d03"
                maxWidth = "400px"
            }
            h2 {
                attrs.style = js {
                    margin = "auto"
                    marginTop = "10px"
                }
                +"Join room"
            }
            form {
                attrs.id = idForm
                attrs.method = FormMethod.post
                attrs.action = "TO_SET"
                attrs.style = js {
                    display = "flex"
                    flexDirection = "column"
                    margin = "auto"
                }
                p {
                    +"Username"
                    attrs.style = js {
                        margin = "auto"
                        marginTop = "10px"
                    }
                }
                input {
                    attrs.type = InputType.text
                    attrs.id = "username"
                    attrs.style = js {
                        margin = "auto"
                        marginTop = "10px"
                        marginBottom = "10px"
                    }
                }
                p {
                    +"Choose room"
                    attrs.style = js {
                        margin = "auto"
                        marginTop = "10px"
                    }
                }
                select {
                    attrs.form = idForm
                    attrs.id = "idSession"
                    attrs.style = js {
                        margin = "auto"
                        marginTop = "10px"
                        marginBottom = "10px"
                        minWidth = "100px"
                    }
                        if(state.sessions != null) {
                            for (session in state.sessions) {
                                option {
                                    attrs.value = "${session.id}"
                                    + "${session.name}"
                                }
                            }
                        }

                }
                input {
                    attrs.type = InputType.submit
                    attrs.value = "Join"
                    attrs.style = js {
                        margin = "auto"
                        marginTop = "10px"
                        marginBottom = "10px"
                        minWidth = "100px"
                    }
                }
            }
        }
    }
}

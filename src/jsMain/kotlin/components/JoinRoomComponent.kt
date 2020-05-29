package components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

class JoinRoomComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        p {
            +"join room"
        }
    }

}
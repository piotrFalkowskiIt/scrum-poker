import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

class CreateRoomComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        p {
            +"create room"
        }
    }

}

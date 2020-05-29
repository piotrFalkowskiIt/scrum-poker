package components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

class CreateTaskComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        p {
            +"create task component added by extension function"
        }
    }
}

internal fun RBuilder.createTaskComponent() = child(CreateTaskComponent::class) {
}

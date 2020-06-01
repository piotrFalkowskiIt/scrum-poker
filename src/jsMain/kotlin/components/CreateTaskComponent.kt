package components

import kotlinext.js.js
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.style
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.input

class CreateTaskComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            attrs.style = js {
                display = "flex"
                flexDirection = "row"
                border = "1px solid #9c0d03"
                maxWidth = "400px"
            }
            input {
                attrs.type = InputType.text
                attrs.id = "taskTitle"
                attrs.placeholder = "Task title..."
                attrs.style = js {
                    margin = "auto"
                    marginTop = "10px"
                    marginBottom = "10px"
                }
            }
            input {
                attrs.type = InputType.submit
                attrs.value = "Create task"
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

internal fun RBuilder.createTaskComponent() = child(CreateTaskComponent::class) {
}

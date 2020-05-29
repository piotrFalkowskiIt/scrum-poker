package components

import components.dashboard.voteComponent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p

class DashboardComponent : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        p {
            +"dashboard component"
        }
        createTaskComponent()
        voteComponent {
            taskName = "TASK NAME"
            taskId = "ID"
        }
    }

}

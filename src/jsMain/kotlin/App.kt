import components.DashboardComponent
import components.JoinRoomComponent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        hashRouter {
            switch {
                route("/", CreateRoomComponent::class, exact = true)
                route("/join", JoinRoomComponent::class, exact = true)
                route("/dashboard", DashboardComponent::class, exact = true)
            }
        }
    }
}

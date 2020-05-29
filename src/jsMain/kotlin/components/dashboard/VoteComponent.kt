package components.dashboard

import kotlinext.js.js
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import kotlinx.html.style
import react.*
import react.dom.div
import react.dom.input
import react.dom.p

interface VoteComponentProps : RProps {
    var taskId: String;
    var taskName: String;
}

interface VoteTilesProps : RProps {
    var value: Int
    var currentValue: Int?
    var changeValue: (Int) -> Unit?
    var confirmVote: () -> Unit?
}
data class VoteState(
        val currentVote: Int
): RState
private val voteTiles = functionalComponent<VoteTilesProps> { p ->
    div {
        attrs {
            style = js {
                width = "250px"
                heigth = "100px"
                border = "1px solid #9c0d03"
                textAlign = "center"
                fontSize = "20px"
                color = "blue"
                backgroundColor = if (p.value == p.currentValue) {
                    "red"
                } else {
                    "pink"
                }
                cursor = "pointer"
            }
            onClickFunction = {
                p.changeValue(p.value)
            }
        }
        +p.value.toString()
        if (p.value == p.currentValue) {
            input(type = InputType.button) {
                attrs {
                    onClickFunction = {
                       p.confirmVote()
                    }
                    value = "Confirm"
                }
            }
        } else {
            + "Not this"
        }
    }
}

fun RBuilder.voteTiles(handler: VoteTilesProps.() -> Unit) = child(voteTiles) {
    attrs(handler)
}

class VoteComponent : RComponent<VoteComponentProps, VoteState> {
    constructor(props: VoteComponentProps): super(props){
        this.setState(VoteState(-1));
    }
    private fun fibonacciSequence() = sequence {
        var terms = Pair(1, 2)

        // this sequence is infinite
        while (true) {
            yield(terms.first)
            terms = Pair(terms.second, terms.first + terms.second)
        }
    }

    private fun fibonacci(n: Int) = fibonacciSequence().take(n).toList()
    private fun updateVote(value: Int) {
        this.setState(VoteState(value))
    }
    private fun sendVote(){
        this.state.currentVote;
    }
    override fun RBuilder.render() {
        div {
            p {
                +" VOTE: ${props.taskName}"
            }
            div {
                attrs.style = js {
                    display = "flex"
                    flexDirection = "row"
                    border = "1px solid #9c0d03"
                    maxWidth = "1010px"
                    flexWrap = "wrap"
                }
                fibonacci(7).map {
                    voteTiles {
                        value = it
                        currentValue = 5
                        changeValue = this@VoteComponent::updateVote
                        confirmVote = this@VoteComponent::sendVote
                    }
                }
            }
        }
    }
}

fun RBuilder.voteComponent(handler: VoteComponentProps.() -> Unit): ReactElement {
    return child(VoteComponent::class) {
        this.attrs(handler)
    }
}

package components

import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import kotlin.browser.document

interface GitHubAuthProps : RProps {
    var onClickAuthButton: (GitHubAuthComponent.(Event) -> Unit)?
}

interface GitHubAuthState : RState

class GitHubAuthComponent : RComponent<GitHubAuthProps, GitHubAuthState>() {
    val username: String get() = (document.getElementById("input-username") as HTMLInputElement).value
    val password: String get() = (document.getElementById("input-password") as HTMLInputElement).value

    override fun RBuilder.render() {
        form {
            div("card bg-light mb-3") {
                div("card-body") {
                    h3("card-title") { +"GitHub Credentials" }
                    div("form-row") {
                        div("form-group col-md-5") {
                            label { +"Username" }
                            input(classes = "form-control") {
                                attrs.id = "input-username"
                                attrs.placeholder = "GitHub username..."
                            }
                        }
                        div("form-group col-md-5") {
                            label { +"Password" }
                            input(classes = "form-control", type = InputType.password) {
                                attrs.id = "input-password"
                                attrs.placeholder = "GitHub password..."
                            }
                        }
                    }
                    div("form-group") {
                        button(classes = "btn btn-primary", type = ButtonType.button) {
                            +"Authenticate"
                            attrs.onClickFunction = { event ->
                                props.onClickAuthButton?.let { it(event) }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.githubAuth(onClickAuthButton: GitHubAuthComponent.(Event) -> Unit) = child(GitHubAuthComponent::class) {
    attrs.onClickAuthButton = onClickAuthButton
}
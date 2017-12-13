package components

import index.Organization
import kotlinx.html.ButtonType
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

// --

interface GitHubOrgProps : RProps {
    var organizations: List<Organization>?
    var onClickAddOrgButton: (GitHubOrgComponent.(Event) -> Unit)?
}

interface GitHubOrgState : RState

class GitHubOrgComponent : RComponent<GitHubOrgProps, GitHubOrgState>() {
    val input: String get() = (document.getElementById("input-organization") as HTMLInputElement).value

    override fun RBuilder.render() {
        form {
            div("card bg-light mb-3") {
                div("card-body") {
                    h3("card-title") { +"Organizations" }
                    div("input-group") {
                        span("input-group-btn") {
                            button(classes = "btn btn-primary", type = ButtonType.button) {
                                +"Add"
                                attrs.onClickFunction = { event ->
                                    props.onClickAddOrgButton?.let { it(event) }
                                }
                            }
                        }
                        input(classes = "form-control") {
                            attrs.id = "input-organization"
                            attrs.placeholder = "GitHub Organization..."
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.githubOrg(organizations: List<Organization>,
                       onClickAddOrgButton: GitHubOrgComponent.(Event) -> Unit) = child(GitHubOrgComponent::class) {
    attrs.organizations
    attrs.onClickAddOrgButton = onClickAddOrgButton
}
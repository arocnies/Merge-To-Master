package index

import components.githubAuth
import kotlinext.js.invoke
import kotlinext.js.require
import kotlinx.html.BUTTON
import kotlinx.html.ButtonType
import kotlinx.html.InputType
import react.RBuilder
import react.dom.*
import kotlin.browser.document

fun main(args: Array<String>) {
    require("src/index/index.css")
    require("src/app/App.css")
    require("src/logo/Logo.css")

    render(document.getElementById("root")) {
        nav("navbar navbar-dark bg-dark sticky-top") {
            div("navbar-brand") { +"Merge-To-Master" }
        }

        div("container") {
            githubAuth { println("User: $username, Pass: $password") }
            organizationPicker()
            branchPicker()

            hr {}

            stupRepos.forEach {
                repoList(it)
            }
        }
    }
}

fun RBuilder.organizationPicker() {
    form {
        div("card bg-light mb-3") {
            div("card-body") {
                h3("card-title") { +"Organizations" }
                div("input-group") {
                    span("input-group-btn") {
                        button(classes = "btn btn-primary") { +"Add" }
                    }
                    input(name = "org-input", classes = "form-control") {
                        attrs.placeholder = "GitHub Organization..."
                    }
                }
            }
        }
    }
}

fun RBuilder.branchPicker() {
    form {
        div("card bg-light mb-3") {
            div("card-body") {
                h3("card-title") { +"Branches" }
                div("form-row") {
                    div("form-group col-md-6") {
                        label { +"Head"; attrs.htmlFor = "input-head-branch" }
                        input(name = "input-head-branch", classes = "form-control") {
                            attrs.defaultValue = "dev"
                        }
                        small("form-text text-muted") { +"The branch that contains your changes." }
                    }
                    div("form-group col-md-6") {
                        label { +"Base"; attrs.htmlFor = "input-head-branch" }
                        input(name = "input-head-branch", classes = "form-control") {
                            attrs.defaultValue = "master"
                        }
                        small("form-text text-muted") { +"The branch where changes should be applied." }
                    }
                }
                div("form-group") {
                    button(classes = "btn btn-primary") { +"Fetch Branches" }
                }
            }
        }
    }
}

fun RBuilder.repoList(repo: Repo) {
    div("card bg-light border-primary mb-3") {
        div("card-body") {
            h4("card-title") { +repo.name }
            +"This is a card body for the repo $repo"
        }
    }
}

val stupRepos = listOf(Repo("omar-avro"), Repo("ossim-ci"), Repo("omar-scdf-stager"))

data class Repo(val name: String)

package index

import kotlinext.js.invoke
import kotlinext.js.require
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
            mainForm()

            hr {}

            stupRepos.forEach {
                repoSelector(it)
            }
        }

//        app()
    }
}

fun RBuilder.mainForm() {
    form {
        div("card bg-light mb-3") {
            div("card-body") {
                h3("card-title") { +"GitHub Credentials" }
                div("form-row") {
                    div("form-group col-md-5") {
                        label { +"Username"; attrs.htmlFor = "input-username" }
                        input(name = "input-username", classes = "form-control") {
                            attrs.placeholder = "GitHub username..."
                        }
                    }
                    div("form-group col-md-5") {
                        label { +"Password"; attrs.htmlFor = "input-password" }
                        input(name = "input-password", classes = "form-control", type = InputType.password) {
                            attrs.placeholder = "GitHub password..."
                        }
                    }
                }
                div("form-group") {
                    button(classes = "btn btn-primary") { +"Authenticate" }
                }
            }
        }
        div("card bg-light mb-3") {
            div("card-body") {
                h3("card-title") { +"Select Branches" }
                div("form-group") {
                    label { +"Organization"; attrs.htmlFor = "org-input" }
                    input(name = "org-input", classes = "form-control form-control-lg") {
                        attrs.placeholder = "GitHub Organization..."
                    }
                }
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

fun RBuilder.repoSelector(repo: Repo) {
    div("card border-primary mb-3") {
//        h4("card-header bg-info text-white") { +repo.name }
        div("card-body") {
            h4("card-title") { +repo.name }
            +"This is a card body for the repo $repo"
        }
    }
}

val stupRepos = listOf(Repo("omar-avro"), Repo("ossim-ci"), Repo("omar-scdf-stager"))

data class Repo(val name: String)

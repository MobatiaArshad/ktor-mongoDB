package com.developers.pk.routes

import com.developers.pk.database.createUpdateUser
import com.developers.pk.database.getUserInDetail
import com.developers.pk.database.getUsers
import com.developers.pk.database.loginUser
import com.developers.pk.models.CommonResponse
import com.developers.pk.models.Users
import com.developers.pk.postRequests.LoginData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.usersRoute() {

    route("/createUpdateUser") {
        post {
            val input = call.receive<Users>()

            if (input.validate().isEmpty()) {
                if (createUpdateUser(input)) {
                    call.respond(HttpStatusCode.OK, CommonResponse(true, "Success", Unit))
                } else {
                    call.respond(HttpStatusCode.Conflict)
                }
            } else {
                call.respond(HttpStatusCode.OK, CommonResponse(false, "Failed", input.validate()))
            }

        }

    }

    route("/allUsers") {
        get {
            call.respond(HttpStatusCode.OK,CommonResponse(true,"Success",getUsers()))
        }
    }

    route("/getUserDetails") {
        get {
            val id = call.receive<Users>().id
            call.respond(HttpStatusCode.OK,getUserInDetail(id))
        }
    }

    route("/loginUser"){
        get {
            val input = call.receive<LoginData>()
            call.respond(HttpStatusCode.OK, loginUser(input.name,input.adaarNumber))
        }
    }

}
package com.developers.pk.database

import com.developers.pk.models.CommonResponse
import com.developers.pk.models.Users
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo


val client = KMongo.createClient().coroutine
val database = client.getDatabase("PeoplesData")

val peoples = database.getCollection<Users>()

suspend fun createUpdateUser(user: Users): Boolean {
    val ifExists = peoples.findOneById(user.id) != null
    return if (ifExists) {
        peoples.updateOneById(user.id,user).wasAcknowledged()
    } else {
        user.id = ObjectId().toString()
        peoples.insertOne(user).wasAcknowledged()
    }
}

suspend fun getUsers(): List<Users> = peoples.find().toList()

suspend fun getUserInDetail(userId: String): CommonResponse<Any> {
    return if (peoples.findOneById(userId) == null) {
        CommonResponse(false,"Failed",mapOf(Pair("message","No data found")))
    } else {
        CommonResponse(true,"Success",peoples.findOneById(userId)!!)
    }
}
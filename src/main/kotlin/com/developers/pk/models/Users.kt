package com.developers.pk.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Users(
    val name: String?= null,
    val place: String?= null,
    val adaarNumber: String?= null,
    val phoneNumber: String?= null,
    @BsonId
    var id: String = ObjectId().toString()
) {
    fun validate(): List<ErrorData> {
        val error = mutableListOf<ErrorData>()
        when {
            name.isNullOrEmpty() -> error.add(ErrorData("name cannot be empty"))
            place.isNullOrEmpty() -> error.add(ErrorData("place cannot be empty"))
            adaarNumber.isNullOrEmpty() -> error.add(ErrorData("adaarNumber cannot be empty"))
            phoneNumber.isNullOrEmpty() -> error.add(ErrorData("phoneNumber cannot be empty"))
        }
        return error
    }
}

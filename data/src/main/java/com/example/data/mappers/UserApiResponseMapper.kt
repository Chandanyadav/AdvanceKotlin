package com.example.data.mappers

import com.example.data.api.UsersApiResponse
import com.example.domain.entities.UserInfo

class UserApiResponseMapper {
    fun toVolumeList(response: UsersApiResponse): List<UserInfo> {
        return null!!
//        response.items.map {
//            Volume(
//                it.id, UserInfo(
//                    it.volumeInfo.title,
//                    it.volumeInfo.authors,
//                    it.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
//                )
//            )
//        }
    }
}
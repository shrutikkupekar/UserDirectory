package com.example.userdirectory.data

import com.example.userdirectory.data.local.UserEntity
import com.example.userdirectory.data.remote.UserDto

fun UserDto.asEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website
    )
}

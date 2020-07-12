package ng.com.gocheck.joke.model

import com.google.gson.annotations.SerializedName

data class Flags(
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)
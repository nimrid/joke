package ng.com.gocheck.joke.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CONSTANT_ID = 0
@Entity
data class Jokes(
    val category: String?,
    val delivery: String?,
    val error: Boolean?,
    @Embedded(prefix = "flags_")
    val flags: Flags?,
    val joke : String?,
    val id: Int,
    val setup: String?,
    val type: String?
) {
    @PrimaryKey(autoGenerate = false)
    var jokeId : Int = CONSTANT_ID
}
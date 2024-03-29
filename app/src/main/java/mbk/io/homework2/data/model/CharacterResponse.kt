package mbk.io.homework2.data.model

import java.io.Serializable

data class CharacterResponse(
    val info: Info,
    val results: List<Character>,
) : Serializable

data class Info(
    val counter: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
) : Serializable

data class Location(
    val name: String,
    val url: String,
) : Serializable

data class Origin(
    val name: String,
    val url: String,
) : Serializable
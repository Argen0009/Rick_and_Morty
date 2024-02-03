package mbk.io.homework2.data.api

import mbk.io.homework2.data.model.Character
import mbk.io.homework2.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    fun getCharacters(
    ):Call<CharacterResponse>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int):Call<Character>
}
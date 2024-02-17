package mbk.io.homework2.data.api

import mbk.io.homework2.data.model.Character
import mbk.io.homework2.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getCharacters(
    ): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int
    ): Response<Character>
}
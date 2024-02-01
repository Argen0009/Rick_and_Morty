package mbk.io.homework2.data.api

import mbk.io.homework2.data.model.Characte
import mbk.io.homework2.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {

    @GET("character")
    fun getCharacters(
    ):Call<CharacterResponse<Characte>>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int):Call<Characte>
}

//interface WeatherApi {
//    @GET("data/2.5/weather")
//    fun getWeather(
//        @Query("q") city: String,
//        @Query("appid") key: String = "bdb2917eb8179d50d760b162dcdc2e24",
//        @Query("units") unit: String = "metric",
//    ): Call<WeatherModel>
//}
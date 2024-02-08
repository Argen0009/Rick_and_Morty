package mbk.io.homework2.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mbk.io.homework2.data.api.CartoonApiService
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.data.model.CharacterResponse
import mbk.io.homework2.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RMRepository @Inject constructor(private val api: CartoonApiService) {
    fun getCharacters(): LiveData<Resource<List<Character>>> {
        val characters = MutableLiveData<Resource<List<Character>>>()
        characters.postValue(Resource.Loading())
        api.getCharacters().enqueue(object : Callback<CharacterResponse> {
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>,
            ) {
                if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                    response.body()?.let {
                        characters.postValue(
                            Resource.Success(it.results)
                        )
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                characters.postValue(Resource.Error(t.message?: "Unknown error!"))
            }
        })
        return characters
    }

    fun getCharacter(id: Int): LiveData<Character> {
        val characteLv = MutableLiveData<Character>()

        api.getCharacter(id).enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>,
            ) {
                response.body()?.let {
                    characteLv.postValue(it)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }

        })
        return characteLv
    }
}
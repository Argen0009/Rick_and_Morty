package mbk.io.homework2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mbk.io.homework2.data.api.CartoonApiService
import mbk.io.homework2.data.model.Characte
import mbk.io.homework2.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RMRepository @Inject constructor(private val api: CartoonApiService) {
    fun getCharacters(): MutableLiveData<List<Characte>> {
        val characters = MutableLiveData<List<Characte>>()

        api.getCharacters().enqueue(object : retrofit2.Callback<CharacterResponse<Characte>> {
            override fun onResponse(
                call: Call<CharacterResponse<Characte>>,
                response: Response<CharacterResponse<Characte>>,
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        characters.postValue(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse<Characte>>, t: Throwable) {
                print(t.message.toString())
            }
        })
        return characters
    }

    fun getCharacter(id: Int): LiveData<Characte> {
        val characteLv = MutableLiveData<Characte>()
        api.getCharacter(id).enqueue(object : Callback<Characte> {
            override fun onResponse(
                call: Call<Characte>,
                response: Response<Characte>,
            ) {
                response.body()?.let {
                    characteLv.postValue(it)
                }
            }

            override fun onFailure(call: Call<Characte>, t: Throwable) {
                print(t.message.toString())
            }

        })
        return characteLv
    }
}
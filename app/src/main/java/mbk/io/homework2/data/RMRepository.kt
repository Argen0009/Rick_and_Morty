import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mbk.io.homework2.data.BaseRepository
import mbk.io.homework2.data.api.CartoonApiService
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.utils.Resource

class RMRepository(private val api: CartoonApiService) : BaseRepository(api) {

    fun getCharacters(): LiveData<Resource<List<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        val result = apiRequest { api.getCharacters() }
        emit(result)
    }
    fun getCharacter(): LiveData<Resource<Character>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        val result = apiRequest { api.getCharacter() }
        emit(result)
    }
}

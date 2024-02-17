
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mbk.io.homework2.data.api.CartoonApiService
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.utils.Resource
import retrofit2.Response
import java.io.IOException
abstract class BaseRepository(private val api: CartoonApiService) {

    protected suspend fun <T> apiRequest(call: suspend () -> Response<T>): Resource<T> {
        return try ({
            val response = call.invoke()
            if (response.isSuccessful) {
                Resource.Success(response.body())
            } else {
                Resource.Error("Error: ${response.code()} ${response.message()}")
            }
        })!! catch (e: IOException) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

}

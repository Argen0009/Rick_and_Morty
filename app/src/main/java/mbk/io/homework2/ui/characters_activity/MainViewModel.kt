package mbk.io.homework2.ui.characters_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mbk.io.homework2.data.RMRepository
import mbk.io.homework2.data.model.Characte
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RMRepository,
) : ViewModel() {

    fun getCaracters(): LiveData<List<Characte>> {
        return repository.getCharacters()
    }
}
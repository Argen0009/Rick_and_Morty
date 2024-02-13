package mbk.io.homework2.ui.secondActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mbk.io.homework2.data.RMRepository
import mbk.io.homework2.data.model.Character
import javax.inject.Inject
class DetailsViewModel (private val repository: RMRepository) : ViewModel() {

    fun getData(id: Int): LiveData<Character> {
        return repository.getCharacter(id)
    }
}
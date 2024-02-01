package mbk.io.homework2.ui.secondActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mbk.io.homework2.data.RMRepository
import mbk.io.homework2.data.model.Characte
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: RMRepository) : ViewModel() {

    fun getData(id: Int): LiveData<Characte> {
        return repository.getCharacter(id)
    }
}
package mbk.io.homework2.ui.secondActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.databinding.ActivityDetailsBinding
import mbk.io.homework2.keys.CharacterKeys

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getIntExtra(CharacterKeys.CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }
        }
    }

    private fun setCharacterData(it: Character) = with(binding) {
        tvCharacterName.text = it.name
        aliveTv.text = it.status
        Glide.with(imgCircleStatus).load(it.image).circleCrop().into(img)

    }

    companion object {
        const val CHARACTER_ID_ARG = "characterIdArg"
    }
}
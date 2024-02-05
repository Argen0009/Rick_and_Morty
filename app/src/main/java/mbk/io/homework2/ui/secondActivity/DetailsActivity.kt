package mbk.io.homework2.ui.secondActivity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import mbk.io.homework2.R
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.databinding.ActivityDetailsBinding
import mbk.io.homework2.keys.CharacterKeys

@AndroidEntryPoint
class DetailsActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()


        val id = intent.getIntExtra(CharacterKeys.CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }
        }
    }

    private fun initClickers() {
        binding.container.setOnClickListener {
            if (binding.exampleData.visibility == View.GONE && binding.exampleName.visibility == View.GONE) {
                binding.exampleData.visibility = View.VISIBLE
                binding.data.visibility = View.VISIBLE
                binding.exampleName.visibility = View.VISIBLE
                binding.tvName.visibility = View.VISIBLE
            } else {
                binding.exampleData.visibility = View.GONE
                binding.data.visibility = View.GONE
                binding.exampleName.visibility = View.GONE
                binding.tvName.visibility = View.GONE
            }
        }
    }

    private fun setCharacterData(it: Character) = with(binding) {
        tvCharacterName.text = it.name
        tvSpecies.text = it.species
        aliveTv.text = it.status
        tvGenderAnswer.text = it.gender
        tvLastKnowLocationAnswer.text = it.location.name
        tvFirstSeenInAnswer.text = it.origin.name
    }

    companion object {
        const val CHARACTER_ID_ARG = "characterIdArg"
    }
}
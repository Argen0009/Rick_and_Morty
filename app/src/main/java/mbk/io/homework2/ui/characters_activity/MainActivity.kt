package mbk.io.homework2.ui.characters_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import mbk.io.homework2.databinding.ActivityMainBinding
import mbk.io.homework2.ui.adapter.RickAdapter
import mbk.io.homework2.ui.secondActivity.DetailsActivity
import mbk.io.homework2.utils.Resource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = RickAdapter(this::onClickItem)

    private val viewModel by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = adapter
        setContentView(binding.root)
        setupRecycler()

       viewModel.getCharacters().observe(this) { state ->
            binding.progressIndicator.isVisible = state is Resource.Loading<*>
            when (state) {
                is Resource.Error<*> -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading<*> -> {
                }

                is Resource.Success<*> -> {
                    if (state.data != null) {
                        adapter.submitList(state.data)
                    }
                }
            }
        }
    }

    private fun setupRecycler() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = adapter
    }

    private fun onClickItem(characteId: Int) {
        startActivity(
            Intent(
                this,
                DetailsActivity::class.java
            ).apply {
                putExtra(DetailsActivity.CHARACTER_ID_ARG, characteId)
            }
        )
    }
}
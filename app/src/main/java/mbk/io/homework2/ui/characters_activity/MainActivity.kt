package mbk.io.homework2.ui.characters_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import mbk.io.homework2.ui.adapter.RickAdapter
import mbk.io.homework2.databinding.ActivityMainBinding
import mbk.io.homework2.ui.secondActivity.DetailsActivity
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = RickAdapter(this::onClickItem)

    private val viewModel by lazy{
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()

        viewModel.getCharacters().observe(this) { character ->
            adapter.setCharacters(character)
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
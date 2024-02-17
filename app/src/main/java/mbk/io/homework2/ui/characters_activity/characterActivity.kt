package mbk.io.homework2.ui.characters_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import mbk.io.homework2.R
import mbk.io.homework2.databinding.ActivityMainBinding
import mbk.io.homework2.ui.secondActivity.DetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    override val recyclerView: RecyclerView by lazy { binding.recyclerView }
    override val viewMode: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun observeViewModel() {
        viewMode.getCharacters().observeResource(
            loadingBlock = { binding.progressIndicator.isVisible = true },
            successBlock = { data -> adapter.submitList(data) },
            errorBlock = { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun getDetailsIntent(characterId: Int): Intent {
        return Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.CHARACTER_ID_ARG, characterId)
        }
    }
}
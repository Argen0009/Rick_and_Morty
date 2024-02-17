package mbk.io.homework2.ui.characters_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mbk.io.homework2.ui.adapter.RickAdapter
import mbk.io.homework2.utils.Resource

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val recyclerView: RecyclerView
    protected abstract val viewMode: MainViewModel

    val adapter = RickAdapter { onClickItem(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setupRecycler()
        observeViewModel()
    }

    protected abstract fun getLayoutResId(): Int

    protected abstract fun observeViewModel()

    private fun setupRecycler() = with(recyclerView) {
        layoutManager = LinearLayoutManager(
            this@BaseActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = this@BaseActivity.adapter
    }

    private fun onClickItem(characteId: Int) {
        startActivity(getDetailsIntent(characteId))
    }

    protected abstract fun getDetailsIntent(characterId: Int): Intent

    protected fun <T> LiveData<Resource<T>>.observeResource(
        loadingBlock: () -> Unit = {},
        successBlock: (data: T?) -> Unit,
        errorBlock: (message: String?) -> Unit
    ) {
        observe(this@BaseActivity) { state ->
            loadingBlock()
            when (state) {
                is Resource.Loading -> loadingBlock()
                is Resource.Success -> successBlock(state.data)
                is Resource.Error -> errorBlock(state.message)
            }
        }
    }
}

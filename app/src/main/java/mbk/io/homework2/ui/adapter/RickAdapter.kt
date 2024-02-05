package mbk.io.homework2.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mbk.io.homework2.CharacterStatus
import mbk.io.homework2.R
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.databinding.ItemCardBinding
import java.util.Locale

class RickAdapter(
    private val onCharacterClick: (Int) -> Unit,
) : RecyclerView.Adapter<RickAdapter.CharacterViewHolder>() {
    private var characte = listOf<Character>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacterViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent,
            false
        )
        return CharacterViewHolder(binding, onCharacterClick)
    }

    override fun getItemCount(): Int = characte.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characte[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacters(list: List<Character>) {
        characte = list
        notifyDataSetChanged()
    }

    class CharacterViewHolder(
        private val binding: ItemCardBinding,

        private val onCharacterClick: (Int) -> Unit

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(characte: Character) {
            binding.tvName.text = characte.name
            binding.aliveTv.text = characte.status
            binding.gender.text = characte.gender
            Glide.with(binding.ivImg).load(characte.image).into(binding.ivImg)

            binding.cardView.setOnClickListener {
                onCharacterClick(characte.id)
            }

            when (CharacterStatus.valueOf(characte.status.uppercase(Locale.getDefault()))) {
                CharacterStatus.ALIVE -> binding.imgCircleStatus.setBackgroundResource(
                    CharacterStatus.ALIVE.drawableResource)
                CharacterStatus.DEAD -> binding.imgCircleStatus.setBackgroundResource(
                    CharacterStatus.DEAD.drawableResource)
                CharacterStatus.UNKNOWN -> binding.imgCircleStatus.setBackgroundResource(
                    CharacterStatus.UNKNOWN.drawableResource)
            }

        }
    }
}
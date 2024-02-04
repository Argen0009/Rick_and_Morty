package mbk.io.homework2.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mbk.io.homework2.R
import mbk.io.homework2.data.model.Character
import mbk.io.homework2.databinding.ItemCardBinding

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

            when (characte.status) {
                "Alive" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
                "Dead" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
                "unknown" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_white)
            }
        }
    }
}
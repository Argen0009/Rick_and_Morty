package mbk.io.homework2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mbk.io.homework2.R
import mbk.io.homework2.data.model.Characte
import mbk.io.homework2.databinding.ItemCardBinding

class RickAdapter(
    private val onCharacterClick: (Int) -> Unit,
) : RecyclerView.Adapter<RickAdapter.CharacterViewHolder>() {
    private var characte= listOf<Characte>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacterViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(
                parent.context) ,
            parent, false)
        return CharacterViewHolder(binding, onCharacterClick)
    }

    override fun getItemCount(): Int = characte.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characte[position])
    }

    fun setCharacters(list: List<Characte>) {
        characte = list
        notifyDataSetChanged()
    }

    class CharacterViewHolder(
        private val binding: ItemCardBinding,
       private val onCharacterClick: (Int) -> Unit

    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(characte: Characte) {
            binding.nameTv.text = characte.name
            binding.aliveTv.text = characte.gender
            Glide.with(binding.img).load(characte.image).into(binding.img)

            binding.CardView.setOnClickListener {
                onCharacterClick(characte.id)
            }


            when (characte.status) {
                "Alive" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_white)
                "Dead" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_white)
                "unknown" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_white)
            }
        }
    }
}
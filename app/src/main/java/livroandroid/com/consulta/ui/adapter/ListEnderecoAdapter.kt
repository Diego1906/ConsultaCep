package livroandroid.com.consulta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import livroandroid.com.consulta.databinding.ItemEnderecoBinding
import livroandroid.com.consulta.entities.Adress

class ListEnderecoAdapter :
    ListAdapter<Adress, ListEnderecoAdapter.ItemHolder>(
        AdressDiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ItemHolder private constructor(val binding: ItemEnderecoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Adress) {
            binding.adress = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemEnderecoBinding.inflate(inflater, parent, false)
                return ItemHolder(
                    binding
                )
            }
        }
    }
}

class AdressDiffCallBack : DiffUtil.ItemCallback<Adress>() {
    override fun areItemsTheSame(oldItem: Adress, newItem: Adress): Boolean {
        return oldItem.cep == newItem.cep
    }

    override fun areContentsTheSame(oldItem: Adress, newItem: Adress): Boolean {
        return oldItem == newItem
    }
}
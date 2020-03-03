package livroandroid.com.consulta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import livroandroid.com.consulta.databinding.ItemListAdressBinding
import livroandroid.com.consulta.entities.Adress

class AdressListAdapter :
    ListAdapter<Adress, AdressListAdapter.ItemHolder>(
        AdressDiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ItemHolder private constructor(val binding: ItemListAdressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Adress) {
            binding.adress = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemListAdressBinding.inflate(inflater, parent, false)
                return ItemHolder(binding)
            }
        }
    }
}

class AdressDiffCallBack : DiffUtil.ItemCallback<Adress>() {
    override fun areItemsTheSame(oldItem: Adress, newItem: Adress): Boolean {
        return oldItem.zipCode == newItem.zipCode
    }

    override fun areContentsTheSame(oldItem: Adress, newItem: Adress): Boolean {
        return oldItem == newItem
    }
}
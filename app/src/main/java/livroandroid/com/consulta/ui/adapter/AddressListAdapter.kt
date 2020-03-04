package livroandroid.com.consulta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import livroandroid.com.consulta.databinding.ItemListAddressBinding
import livroandroid.com.consulta.model.AddressObject

class AddressListAdapter :
    ListAdapter<AddressObject, AddressListAdapter.ItemHolder>(
        AddressDiffCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ItemHolder private constructor(private val binding: ItemListAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddressObject) {
            binding.address = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemListAddressBinding.inflate(inflater, parent, false)
                return ItemHolder(binding)
            }
        }
    }
}

class AddressDiffCallBack : DiffUtil.ItemCallback<AddressObject>() {
    override fun areItemsTheSame(oldItem: AddressObject, newItem: AddressObject): Boolean {
        return oldItem.postalCode == newItem.postalCode
    }

    override fun areContentsTheSame(oldItem: AddressObject, newItem: AddressObject): Boolean {
        return oldItem == newItem
    }
}
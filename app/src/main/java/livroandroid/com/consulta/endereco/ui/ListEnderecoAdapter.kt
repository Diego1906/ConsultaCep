package livroandroid.com.consulta.endereco.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_endereco.view.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.entities.Adress

class ListEnderecoAdapter : RecyclerView.Adapter<ListEnderecoAdapter.ItemHolder>() {

    var listAdress = listOf<Adress>()
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.from(parent)
    }

    override fun getItemCount() = listAdress.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = listAdress[position]
        holder.bind(item)
    }

    class ItemHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Adress) {
            itemView.txt_cep_item.text = item.cep
            itemView.txt_rua_item.text = item.rua
            itemView.txt_bairro_item.text = item.bairro
        }

        companion object {
            fun from(parent: ViewGroup): ItemHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_endereco, parent, false)

                return ItemHolder(view)
            }
        }
    }
}
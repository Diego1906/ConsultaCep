package livroandroid.com.consulta.endereco.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_endereco.view.*
import livroandroid.com.consulta.R
import livroandroid.com.consulta.cep.entities.Adress

class ListEnderecoAdapter : RecyclerView.Adapter<ItemHolder>() {

    var listEndereco = listOf<Adress>()
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_endereco, parent, false)

        return ItemHolder(view)
    }

    override fun getItemCount() = listEndereco.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = listEndereco[position]
        holder.cep.text = item.cep
        holder.rua.text = item.rua
        holder.bairro.text = item.bairro
    }
}

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cep = itemView.txt_cep_item
    val rua = itemView.txt_rua_item
    val bairro = itemView.txt_bairro_item
}
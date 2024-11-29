package com.controlevacinacao.extiv.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.controlevacinacao.extiv.R

class VacinaAdapter(private val vacinaList: ArrayList<Vacina>):
    RecyclerView.Adapter<VacinaAdapter.VacinaViewHolder>(){

    var onItemClick: ((Vacina) -> Unit)? = null

    class VacinaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.tvVacinaItem)
        val checkBox: CheckBox = itemView.findViewById(R.id.cbVacinaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacinaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vacina_item, parent, false)
        return VacinaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vacinaList.size
    }

    override fun onBindViewHolder(holder: VacinaViewHolder, position: Int) {
        val vacina = vacinaList[position]
        holder.textView.text = vacina.nome

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(vacina)
        }
    }
}
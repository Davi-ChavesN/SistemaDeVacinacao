package com.controlevacinacao.extiv.model

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.controlevacinacao.extiv.R

class PetAdapter(private val petList: ArrayList<Pet>):
    RecyclerView.Adapter<PetAdapter.PetViewHolder>(){

    var onItemClick: ((Pet) -> Unit)? = null

    class PetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.iv_pet_item)
        val textView: TextView = itemView.findViewById(R.id.tv_pet_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pet_item, parent, false)
        return PetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = petList[position]
        holder.imageView.setImageResource(R.drawable.bee)
        holder.textView.text = pet.nome

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(pet)
        }
    }

    fun updateList(newList: ArrayList<Pet>) {
        petList.clear()
        petList.addAll(newList)
        notifyDataSetChanged()
    }
}
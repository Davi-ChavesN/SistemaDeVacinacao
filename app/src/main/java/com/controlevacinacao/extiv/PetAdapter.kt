package com.controlevacinacao.extiv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetAdapter(private val petList: ArrayList<Pet>): RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    class PetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val ll_pet_item_horizontal: LinearLayout = itemView.findViewById(R.id.ll_pet_item_horizontal)
        val iv_pet_item: ImageView = itemView.findViewById(R.id.iv_pet_item)
        val ll_pet_item_vertical: LinearLayout = itemView.findViewById(R.id.ll_pet_item_vertical)
        val tv_name_pet_item: TextView = itemView.findViewById(R.id.tv_name_pet_item)
        val tv_breed_pet_item: TextView = itemView.findViewById(R.id.tv_breed_pet_item)
        val tv_size_pet_item: TextView = itemView.findViewById(R.id.tv_size_pet_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.pet_item,
            parent, false)
        return PetViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val currentPet = petList[position]
        holder.tv_name_pet_item.setText(currentPet.nome)
        holder.tv_breed_pet_item.setText(currentPet.raca)
        holder.tv_size_pet_item.setText(currentPet.porte)
    }

    override fun getItemCount(): Int {
        return petList.size
    }
}


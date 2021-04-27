package com.alexanderpodkopaev.marvelcharacters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.model.CharacterModel
import com.bumptech.glide.Glide

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val charactersList: MutableList<CharacterModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character,parent,false)
        )
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(charactersList[position])
    }

    fun bindCharacters(characters: List<CharacterModel>) {
        charactersList.clear()
        charactersList.addAll(characters)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)

        fun bind(character: CharacterModel) {
            tvName.text = character.name
            Glide.with(itemView.context).load(character.image).into(ivImage)
        }
    }
}
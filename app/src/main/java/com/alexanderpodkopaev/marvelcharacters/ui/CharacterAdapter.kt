package com.alexanderpodkopaev.marvelcharacters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexanderpodkopaev.marvelcharacters.R
import com.alexanderpodkopaev.marvelcharacters.data.model.CharacterModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class CharacterAdapter :
    PagingDataAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(CHARACTER_COMPARATOR) {

    var clickListener: CharacterClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class CharacterViewHolder(itemView: View, clickListener: CharacterClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val ivImage = itemView.findViewById<ImageView>(R.id.iv_image)
        private val clCharacter = itemView.findViewById<ConstraintLayout>(R.id.cl_item_character)

        fun bind(character: CharacterModel) {
            tvName.text = character.name
            Glide.with(itemView.context).load(character.image).placeholder(R.drawable.a)
                .transform(
                    CenterCrop(), RoundedCorners(
                        itemView.context.resources.getDimension(
                            R.dimen.standard
                        ).toInt()
                    )
                )
                .into(ivImage)
            clCharacter.transitionName = character.name
            itemView.setOnClickListener {
                clickListener?.onClick(clCharacter, character)
            }
        }
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: CharacterModel,
                newItem: CharacterModel
            ): Boolean =
                oldItem == newItem
        }
    }
}

interface CharacterClickListener {
    fun onClick(view: View, character: CharacterModel)
}
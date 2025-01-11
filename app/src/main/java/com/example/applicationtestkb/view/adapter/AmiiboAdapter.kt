package com.example.applicationtestkb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationtestkb.R
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.databinding.ItemAmiiboCharacterBinding
import com.example.applicationtestkb.view.callbacks.ItemCharacterCallback
import com.squareup.picasso.Picasso

class AmiiboAdapter(
    val amiiboCharacterList: List<CharacterAmiibo>,
    val mCallback: ItemCharacterCallback

) : RecyclerView.Adapter<AmiiboAdapter.AmiiboCharacterViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AmiiboAdapter.AmiiboCharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AmiiboCharacterViewHolder(layoutInflater.inflate(R.layout.item_amiibo_character, parent, false))
    }

    override fun onBindViewHolder(holder: AmiiboAdapter.AmiiboCharacterViewHolder, position: Int) {
        val amiiboCharacter = amiiboCharacterList[position]
        holder.bind(amiiboCharacter, mCallback)
    }

    override fun getItemCount() = amiiboCharacterList.size


    class AmiiboCharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemAmiiboCharacterBinding.bind(view)
        fun bind(amiiboCharacter: CharacterAmiibo, mCallback: ItemCharacterCallback) {
            Picasso.get().load(amiiboCharacter.image).into(binding.ivItemCharacter)
            binding.tvItemCharacter.text = amiiboCharacter.name
            binding.ivItemCharacter.setOnClickListener{
                mCallback.showCharacterDetail(amiiboCharacter)
            }

        }

    }

}
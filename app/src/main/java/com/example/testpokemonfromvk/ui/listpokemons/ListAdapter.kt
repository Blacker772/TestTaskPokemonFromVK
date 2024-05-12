package com.example.testpokemonfromvk.ui.listpokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.testpokemonfromvk.data.pokemons.listpokemons.PokemonResult
import com.example.testpokemonfromvk.databinding.PokemonItemBinding

class ListAdapter: PagingDataAdapter<PokemonResult, ListAdapter.MainViewHolder>(DIFF_UTIL){

    private var onItemClick:((pokemon: PokemonResult) -> Unit)? = null
    fun onItemClickListener(onItemClick: (pokemon: PokemonResult) -> Unit){
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClick) }
        holder.setIsRecyclable(false)

    }
    inner class MainViewHolder(private val binding: PokemonItemBinding): ViewHolder(binding.root){
        fun bind(data: PokemonResult, onItemClick: ((pokemon: PokemonResult) -> Unit)?){
            binding.sivImage.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${data.url?.split("/")?.dropLast(1)?.last()}.png")
            binding.tvName.text = data.name?.uppercase()
            binding.cvItem.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<PokemonResult>() {
            override fun areItemsTheSame(
                oldItem: PokemonResult,
                newItem: PokemonResult
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonResult,
                newItem: PokemonResult
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}
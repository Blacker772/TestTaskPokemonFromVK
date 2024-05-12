package com.example.testpokemonfromvk.ui.listpokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testpokemonfromvk.R
import com.example.testpokemonfromvk.databinding.FragmentListBinding
import com.example.testpokemonfromvk.ui.detailspokemon.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter = ListAdapter()
    private val viewModelPokemon: ListViewModel by viewModels<ListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        lifecycleScope.launch {
            viewModelPokemon.pokemonList.collect {
                adapter.submitData(it)
            }

        }
    }

    private fun initRecyclerView() {
        binding.rvListPokemon.adapter = adapter
        binding.rvListPokemon.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter.onItemClickListener {
            val bundle = bundleOf(
                "name" to it.name
            )
            parentFragmentManager.beginTransaction()
                .addToBackStack(ListFragment::class.java.canonicalName)
                .replace(R.id.fragmentContainer, DetailsFragment::class.java, bundle)
                .commit()
        }
    }

}


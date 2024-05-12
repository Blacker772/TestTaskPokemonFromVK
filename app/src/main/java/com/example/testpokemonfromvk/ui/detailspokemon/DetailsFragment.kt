package com.example.testpokemonfromvk.ui.detailspokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.testpokemonfromvk.databinding.FragmentDetailsBinding
import com.google.gson.annotations.SerializedName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString("name") ?: "null"

        lifecycleScope.launch {
            viewModel.getInfoPokemon(name)
            viewModel.liveData.observe(viewLifecycleOwner) { data ->
                val array = ArrayList<SlideModel>()
                array.add(SlideModel(data.sprites.frontDefault))
                array.add(SlideModel(data.sprites.backDefault))
                array.add(SlideModel(data.sprites.frontFemale))
                array.add(SlideModel(data.sprites.backFemale))
                array.add(SlideModel(data.sprites.frontShiny))
                array.add(SlideModel(data.sprites.backShiny))
                array.add(SlideModel(data.sprites.frontShinyFemale))
                array.add(SlideModel(data.sprites.backShinyFemale))
                binding.imageSlider.setImageList(array)
                binding.imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT)
                binding.imageSlider.startSliding()
                binding.tvName.text = data.name.uppercase()
            }
        }
    }
}
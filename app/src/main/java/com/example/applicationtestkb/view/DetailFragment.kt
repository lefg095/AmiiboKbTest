package com.example.applicationtestkb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.applicationtestkb.R
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.state.DataState
import com.example.applicationtestkb.data.state.FavoritesStateEvent
import com.example.applicationtestkb.data.viewmodel.FavoritesViewModel
import com.example.applicationtestkb.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding: FragmentCharacterDetailBinding
    private val favoritesViewModel by activityViewModels<FavoritesViewModel>()
    var amiiboCharacter: CharacterAmiibo? = null
    private  var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consultingIsFavorite()
        amiiboCharacter = requireArguments().getParcelable("characterDetail")
        showData()
    }

    private fun consultingIsFavorite() {
        favoritesViewModel.isFavoriteResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Loading -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                is DataState.Success -> {
                    isFavorite = it.response
                    setHasOptionsMenu(true)
                }
                is DataState.Error -> Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)
        menu.findItem(R.id.menu_favs)?.let { setFavoriteIcon(it) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setFavoriteIcon(menuItem: MenuItem){
        val idMenu =
            if (isFavorite) {
                R.drawable.love_red
            } else {
                R.drawable.love_empty
            }

        menuItem.icon = ContextCompat.getDrawable(requireContext(), idMenu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favs -> {
                isFavorite = !isFavorite
                setFavoriteIcon(item)
                addOrRemoveFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addOrRemoveFavorite() {
        amiiboCharacter?.let {
            favoritesViewModel.makeApiCall(
                FavoritesStateEvent.AddOrRemoveFavorite(it)
            )
        }
        favoritesViewModel.addOrRemoveFavoriteResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Loading -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                is DataState.Success -> Toast.makeText(requireContext(), it.response, Toast.LENGTH_LONG).show()
                is DataState.Error -> Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun showData() {
        amiiboCharacter?.let {
            Picasso.get().load(it.image).into(binding.ivItemCharacter)
            binding.tvNameCharacter.text = it.name
            binding.tvSeriesCharacter.text = getString(R.string.series, it.amiiboSeries)
            binding.tvtypeCharacter.text = getString(R.string.type, it.type)
            binding.tvHeadCharacter.text = getString(R.string.head, it.head)
            binding.tvTailCharacter.text = getString(R.string.tail, it.tail)
        }

    }

}
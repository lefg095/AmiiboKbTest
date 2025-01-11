package com.example.applicationtestkb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.applicationtestkb.R
import com.example.applicationtestkb.data.model.CharacterAmiibo
import com.example.applicationtestkb.data.state.AmiiboStateEvent
import com.example.applicationtestkb.data.state.DataState
import com.example.applicationtestkb.data.state.FavoritesStateEvent
import com.example.applicationtestkb.data.viewmodel.AmiiboViewModel
import com.example.applicationtestkb.data.viewmodel.FavoritesViewModel
import com.example.applicationtestkb.databinding.FragmentDashboardBinding
import com.example.applicationtestkb.view.adapter.AmiiboAdapter
import com.example.applicationtestkb.view.callbacks.ItemCharacterCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(), ItemCharacterCallback {
    private lateinit var binding: FragmentDashboardBinding
    private val amiiboViewModel by activityViewModels<AmiiboViewModel>()
    private val favoritesViewModel by activityViewModels<FavoritesViewModel>()
    private var adapter: AmiiboAdapter? = null
    var showOnlyFavorites = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            showOnlyFavorites = it.getBoolean("showOnlyFavorites", false)
        }
        if (showOnlyFavorites) {
            getOnlyFavorites()
        } else {
            getAmiiboList()
        }
    }

    private fun getOnlyFavorites() {
        favoritesViewModel.makeApiCall(FavoritesStateEvent.GetFavoritesList)
        favoritesViewModel.favoritesListResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                is DataState.Success -> {
                    initRecylcerView(it.response.amiiboList)
                }
                is DataState.Error -> Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getAmiiboList(){
        amiiboViewModel.getAmiiboList(AmiiboStateEvent.GetAmiiboLit)
        amiiboViewModel.amiiboListResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                is DataState.Success -> {
                    initRecylcerView(it.response.amiiboList)
                }
                is DataState.Error -> Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initRecylcerView(amiiboList: List<CharacterAmiibo>) {
        adapter = AmiiboAdapter(amiiboList, this)
        binding.rvDashboard.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvDashboard.adapter = adapter
    }

    override fun showCharacterDetail(amiiboCharacter: CharacterAmiibo) {
        favoritesViewModel.makeApiCall(FavoritesStateEvent.IsFavorite(amiiboCharacter.tail))
        val bundle = bundleOf("characterDetail" to amiiboCharacter)
        view?.findNavController()?.navigate(R.id.detailFragment, bundle)
    }
}
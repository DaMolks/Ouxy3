package com.damolks.ouxy3.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.damolks.ouxy3.core.event.OuxyEventBus
import com.damolks.ouxy3.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
        subscribeToEvents()
    }

    private fun setupUI() {
        // Setup des vues et listeners
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.refreshLayout.isRefreshing = state is HomeState.Loading
            when (state) {
                is HomeState.Loading -> {
                    binding.contentGroup.visibility = View.GONE
                    binding.errorGroup.visibility = View.GONE
                }
                is HomeState.Content -> {
                    binding.contentGroup.visibility = View.VISIBLE
                    binding.errorGroup.visibility = View.GONE
                    updateContent(state)
                }
                is HomeState.Error -> {
                    binding.contentGroup.visibility = View.GONE
                    binding.errorGroup.visibility = View.VISIBLE
                    binding.errorText.text = state.message
                }
            }
        }
    }

    private fun updateContent(state: HomeState.Content) {
        binding.technicianName.text = state.technicianName
        binding.siteCount.text = state.siteCount.toString()
        // Mettre à jour les autres vues avec les données de l'état
    }

    private fun subscribeToEvents() {
        // S'abonner aux événements pertinents
        OuxyEventBus.subscribe("SITE_UPDATED")
            .onEach { viewModel.refresh() }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
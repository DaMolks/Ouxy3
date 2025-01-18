package com.damolks.ouxy3.ui.main.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.damolks.ouxy3.data.repository.SiteRepository
import com.damolks.ouxy3.databinding.FragmentSitesBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SitesFragment : Fragment() {

    private var _binding: FragmentSitesBinding? = null
    private val binding get() = _binding!!
    private val siteRepository: SiteRepository by inject()
    private lateinit var sitesAdapter: SitesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadSites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        sitesAdapter = SitesAdapter()
        binding.sitesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sitesAdapter
        }

        binding.fabAddSite.setOnClickListener {
            // TODO: Implémenter l'ajout de site
        }
    }

    private fun loadSites() {
        viewLifecycleOwner.lifecycleScope.launch {
            siteRepository.getAllSites().collect { sites ->
                sitesAdapter.submitList(sites)
            }
        }
    }
}
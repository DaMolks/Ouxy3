package com.damolks.ouxy3.ui.onboarding.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.FragmentSitesSetupBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SitesSetupFragment : Fragment() {

    private var _binding: FragmentSitesSetupBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SitesSetupViewModel by viewModel()
    private val siteAdapter = SiteAdapter { site -> viewModel.deleteSite(site) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSitesSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupButtons()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.sitesList.adapter = siteAdapter
    }

    private fun setupButtons() {
        binding.addSiteButton.setOnClickListener {
            showAddSiteDialog()
        }

        binding.finishButton.setOnClickListener {
            viewModel.finishSetup()
        }
    }

    private fun observeViewModel() {
        viewModel.sites.observe(viewLifecycleOwner) { sites ->
            siteAdapter.submitList(sites)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SitesSetupState.Error -> {
                    Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                }
                is SitesSetupState.SiteAdded -> {
                    Snackbar.make(binding.root, R.string.site_added, Snackbar.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is SitesSetupEvent.NavigateToMain -> {
                    findNavController().navigate(R.id.action_sites_to_main)
                    viewModel.clearEvent()
                }
                null -> {}
            }
        }
    }

    private fun showAddSiteDialog() {
        AddSiteDialog().apply {
            onSiteAdded = { name, address, clientName ->
                viewModel.addSite(name, address, clientName)
            }
        }.show(childFragmentManager, AddSiteDialog.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
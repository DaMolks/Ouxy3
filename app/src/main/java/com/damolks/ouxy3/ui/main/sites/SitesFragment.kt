package com.damolks.ouxy3.ui.main.sites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.damolks.ouxy3.data.model.Site
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.damolks.ouxy3.databinding.FragmentSitesBinding
import com.damolks.ouxy3.databinding.DialogAddSiteBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SitesFragment : Fragment() {

    private var _binding: FragmentSitesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SitesViewModel by viewModel()
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
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUI() {
        sitesAdapter = SitesAdapter(
            onSiteClick = { site ->
                showEditSiteDialog(site)
            },
            onDeleteClick = { site ->
                showDeleteConfirmation(site)
            }
        )

        binding.sitesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sitesAdapter
        }

        binding.fabAddSite.setOnClickListener {
            showAddSiteDialog()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.sites.collect { sites ->
                sitesAdapter.submitList(sites)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is SiteEvent.Success -> Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    is SiteEvent.Error -> Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showAddSiteDialog() {
        val dialogBinding = DialogAddSiteBinding.inflate(layoutInflater)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Ajouter un site")
            .setView(dialogBinding.root)
            .setPositiveButton("Ajouter") { dialog, _ ->
                val site = Site(
                    name = dialogBinding.siteName.text.toString(),
                    address = dialogBinding.siteAddress.text.toString(),
                    contactName = dialogBinding.contactName.text.toString(),
                    contactPhone = dialogBinding.contactPhone.text.toString(),
                    notes = dialogBinding.notes.text.toString()
                )
                viewModel.addSite(site)
                dialog.dismiss()
            }
            .setNegativeButton("Annuler") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showEditSiteDialog(site: Site) {
        val dialogBinding = DialogAddSiteBinding.inflate(layoutInflater)

        // Pré-remplir les champs
        with(dialogBinding) {
            siteName.setText(site.name)
            siteAddress.setText(site.address)
            contactName.setText(site.contactName)
            contactPhone.setText(site.contactPhone)
            notes.setText(site.notes)
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Modifier le site")
            .setView(dialogBinding.root)
            .setPositiveButton("Modifier") { dialog, _ ->
                val updatedSite = site.copy(
                    name = dialogBinding.siteName.text.toString(),
                    address = dialogBinding.siteAddress.text.toString(),
                    contactName = dialogBinding.contactName.text.toString(),
                    contactPhone = dialogBinding.contactPhone.text.toString(),
                    notes = dialogBinding.notes.text.toString()
                )
                viewModel.updateSite(updatedSite)
                dialog.dismiss()
            }
            .setNegativeButton("Annuler") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showDeleteConfirmation(site: Site) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Supprimer le site")
            .setMessage("Voulez-vous vraiment supprimer le site ${site.name} ?")
            .setPositiveButton("Supprimer") { dialog, _ ->
                viewModel.deleteSite(site)
                dialog.dismiss()
            }
            .setNegativeButton("Annuler") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
package com.damolks.ouxy3.ui.onboarding.sites

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.damolks.ouxy3.databinding.DialogAddSiteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddSiteDialog : DialogFragment() {

    private var _binding: DialogAddSiteBinding? = null
    private val binding get() = _binding!!
    var onSiteAdded: ((name: String, address: String, clientName: String?) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddSiteBinding.inflate(layoutInflater)

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupButtons()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupButtons() {
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.saveButton.setOnClickListener {
            val name = binding.siteName.text.toString()
            val address = binding.siteAddress.text.toString()
            val clientName = binding.contactName.text.toString()

            if (name.isBlank() || address.isBlank()) {
                binding.siteNameLayout.error = if (name.isBlank()) "Requis" else null
                binding.siteAddressLayout.error = if (address.isBlank()) "Requis" else null
                return@setOnClickListener
            }

            onSiteAdded?.invoke(
                name,
                address,
                clientName.takeIf { it.isNotEmpty() }
            )
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AddSiteDialog"
    }
}
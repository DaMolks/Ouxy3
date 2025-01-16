package com.damolks.ouxy3.ui.onboarding.signature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.FragmentSignatureBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignatureFragment : Fragment() {

    private var _binding: FragmentSignatureBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignatureViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtons()
        observeViewModel()
    }

    private fun setupButtons() {
        binding.clearButton.setOnClickListener {
            binding.signaturePad.clear()
            updateNextButtonState()
        }

        binding.nextButton.setOnClickListener {
            if (binding.signaturePad.isEmpty()) {
                Snackbar.make(binding.root, R.string.error_signature_required, Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Récupérer l'ID du technicien via navigation args
            val technicianId = 1L // Temporaire
            viewModel.saveSignature(
                bitmap = binding.signaturePad.toBitmap(),
                technicianId = technicianId,
                filesDir = requireContext().filesDir
            )
        }

        updateNextButtonState()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SignatureState.Loading -> {
                    binding.progressIndicator.isVisible = true
                    binding.nextButton.isEnabled = false
                }
                is SignatureState.Error -> {
                    binding.progressIndicator.isVisible = false
                    binding.nextButton.isEnabled = true
                    Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    binding.progressIndicator.isVisible = false
                    updateNextButtonState()
                }
            }
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is SignatureEvent.NavigateToSites -> {
                    findNavController().navigate(R.id.action_signature_to_sites)
                    viewModel.clearEvent()
                }
                null -> {}
            }
        }
    }

    private fun updateNextButtonState() {
        binding.nextButton.isEnabled = !binding.signaturePad.isEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
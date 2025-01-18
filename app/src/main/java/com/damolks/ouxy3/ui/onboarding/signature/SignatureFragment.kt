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
        binding.nextButton.isEnabled = false
    }

    private fun setupButtons() {
        binding.clearButton.setOnClickListener {
            binding.signaturePad.clear()
            binding.nextButton.isEnabled = false
        }

        binding.signaturePad.setOnTouchListener { _, event ->
            binding.nextButton.isEnabled = true
            false
        }

        binding.nextButton.setOnClickListener {
            if (binding.signaturePad.isEmpty()) {
                Snackbar.make(binding.root, R.string.error_signature_required, Snackbar.LENGTH_SHORT).show()
                binding.nextButton.isEnabled = false
                return@setOnClickListener
            }

            viewModel.saveSignature(
                bitmap = binding.signaturePad.toBitmap(),
                filesDir = requireContext().filesDir
            )
        }
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
                    binding.nextButton.isEnabled = !binding.signaturePad.isEmpty()
                    Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    binding.progressIndicator.isVisible = false
                    binding.nextButton.isEnabled = !binding.signaturePad.isEmpty()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
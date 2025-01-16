package com.damolks.ouxy3.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.FragmentSignatureBinding
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
        setupSignaturePad()
        setupButtons()
        observeNavigation()
    }

    private fun setupSignaturePad() {
        binding.signaturePad.setOnSignatureChangeListener { hasSignature ->
            binding.nextButton.isEnabled = hasSignature
        }
    }

    private fun setupButtons() {
        binding.clearButton.setOnClickListener {
            binding.signaturePad.clear()
        }

        binding.nextButton.setOnClickListener {
            val signatureBitmap = binding.signaturePad.getSignatureBitmap()
            viewModel.saveSignature(signatureBitmap)
        }
    }

    private fun observeNavigation() {
        viewModel.navigationEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_to_sites)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
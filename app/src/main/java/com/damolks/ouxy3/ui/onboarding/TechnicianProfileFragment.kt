package com.damolks.ouxy3.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.FragmentTechnicianProfileBinding
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class TechnicianProfileFragment : Fragment() {

    private var _binding: FragmentTechnicianProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TechnicianProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTechnicianProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInputListeners()
        setupValidation()
        setupNavigation()
    }

    private fun setupInputListeners() {
        binding.firstNameInput.addTextChangedListener { 
            viewModel.updateFirstName(it?.toString() ?: "")
        }
        binding.lastNameInput.addTextChangedListener {
            viewModel.updateLastName(it?.toString() ?: "")
        }
        binding.sectorInput.addTextChangedListener {
            viewModel.updateSector(it?.toString() ?: "")
        }
        binding.matriculeInput.addTextChangedListener {
            viewModel.updateMatricule(it?.toString() ?: "")
        }
        binding.teamLeaderInput.addTextChangedListener {
            viewModel.updateTeamLeader(it?.toString() ?: "")
        }
    }

    private fun setupValidation() {
        viewModel.formState.observe(viewLifecycleOwner) { state ->
            binding.firstNameLayout.showErrorIfInvalid(state.firstNameError)
            binding.lastNameLayout.showErrorIfInvalid(state.lastNameError)
            binding.sectorLayout.showErrorIfInvalid(state.sectorError)
            binding.matriculeLayout.showErrorIfInvalid(state.matriculeError)
            binding.teamLeaderLayout.showErrorIfInvalid(state.teamLeaderError)
            binding.nextButton.isEnabled = state.isValid
        }
    }

    private fun setupNavigation() {
        binding.nextButton.setOnClickListener {
            viewModel.saveAndContinue()
        }

        viewModel.navigationEvent.observe(viewLifecycleOwner) { event ->
            event?.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_to_signature)
            }
        }
    }

    private fun TextInputLayout.showErrorIfInvalid(error: Int?) {
        this.error = error?.let { getString(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
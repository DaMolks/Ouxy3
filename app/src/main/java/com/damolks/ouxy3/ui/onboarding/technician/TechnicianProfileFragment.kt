package com.damolks.ouxy3.ui.onboarding.technician

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.FragmentTechnicianProfileBinding
import com.google.android.material.snackbar.Snackbar
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

        setupFormListeners()
        observeViewModel()
        setupNextButton()
    }

    private fun setupFormListeners() {
        with(binding) {
            // Mettre à jour le state du formulaire à chaque changement
            firstNameEdit.addTextChangedListener { updateFormState() }
            lastNameEdit.addTextChangedListener { updateFormState() }
            matriculeEdit.addTextChangedListener { updateFormState() }
            sectorEdit.addTextChangedListener { updateFormState() }
            teamLeaderEdit.addTextChangedListener { updateFormState() }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TechnicianProfileState.Loading -> {
                    binding.progressIndicator.isVisible = true
                    binding.nextButton.isEnabled = false
                }
                is TechnicianProfileState.Error -> {
                    binding.progressIndicator.isVisible = false
                    binding.nextButton.isEnabled = true
                    Snackbar.make(binding.root, R.string.error_field_required, Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    binding.progressIndicator.isVisible = false
                    binding.nextButton.isEnabled = true
                }
            }
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is TechnicianProfileEvent.NavigateToSignature -> {
                    findNavController().navigate(R.id.action_profile_to_signature)
                    viewModel.clearEvent()
                }
                null -> {}
            }
        }
    }

    private fun setupNextButton() {
        binding.nextButton.setOnClickListener {
            val formState = TechnicianFormState(
                firstName = binding.firstNameEdit.text.toString(),
                lastName = binding.lastNameEdit.text.toString(),
                sector = binding.sectorEdit.text.toString(),
                matricule = binding.matriculeEdit.text.toString(),
                teamLeader = binding.teamLeaderEdit.text.toString()
            )
            viewModel.saveTechnician(formState)
        }
    }

    private fun updateFormState() {
        val formState = TechnicianFormState(
            firstName = binding.firstNameEdit.text.toString(),
            lastName = binding.lastNameEdit.text.toString(),
            sector = binding.sectorEdit.text.toString(),
            matricule = binding.matriculeEdit.text.toString(),
            teamLeader = binding.teamLeaderEdit.text.toString()
        )
        binding.nextButton.isEnabled = with(formState) {
            firstName.isNotBlank() && lastName.isNotBlank() &&
            sector.isNotBlank() && matricule.isNotBlank() &&
            teamLeader.isNotBlank()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
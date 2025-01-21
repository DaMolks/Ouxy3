package com.damolks.ouxy3.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.damolks.ouxy3.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProfileState.Loading -> {
                    binding.progressIndicator.isVisible = true
                    binding.content.isVisible = false
                }
                is ProfileState.Error -> {
                    binding.progressIndicator.isVisible = false
                    binding.content.isVisible = false
                    Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                }
                is ProfileState.Content -> {
                    binding.progressIndicator.isVisible = false
                    binding.content.isVisible = true
                    updateUI(state)
                }
            }
        }
    }

    private fun updateUI(state: ProfileState.Content) {
        with(binding) {
            technicianName.text = state.technicianName
            matricule.text = state.matricule
            sector.text = state.sector
            teamLeader.text = state.teamLeader
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
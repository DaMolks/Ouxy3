package com.damolks.ouxy3.ui.main.reports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.damolks.ouxy3.databinding.FragmentReportsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReportsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ReportsState.Loading -> {
                    binding.progressIndicator.isVisible = true
                    binding.emptyStateGroup.isVisible = false
                    binding.reportsList.isVisible = false
                }
                is ReportsState.Empty -> {
                    binding.progressIndicator.isVisible = false
                    binding.emptyStateGroup.isVisible = true
                    binding.reportsList.isVisible = false
                }
                is ReportsState.Content -> {
                    binding.progressIndicator.isVisible = false
                    binding.emptyStateGroup.isVisible = false
                    binding.reportsList.isVisible = true
                    // TODO: Setup reports list
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.damolks.ouxy3.debug.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.damolks.ouxy3.databinding.FragmentModuleListBinding
import com.damolks.ouxy3.debug.ModuleMonitor
import com.damolks.ouxy3.debug.adapters.ModuleStateAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ModuleListFragment : Fragment() {
    private var _binding: FragmentModuleListBinding? = null
    private val binding get() = _binding!!
    private val moduleMonitor: ModuleMonitor by inject()
    private lateinit var moduleAdapter: ModuleStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModuleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeModuleStates()
    }

    private fun setupRecyclerView() {
        moduleAdapter = ModuleStateAdapter()
        binding.moduleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moduleAdapter
        }
    }

    private fun observeModuleStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            moduleMonitor.moduleStates.collect { states ->
                moduleAdapter.submitList(states.toList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.damolks.ouxy3.debug.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.damolks.ouxy3.databinding.FragmentErrorListBinding
import com.damolks.ouxy3.debug.ModuleMonitor
import com.damolks.ouxy3.debug.adapters.ErrorAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ErrorListFragment : Fragment() {
    private var _binding: FragmentErrorListBinding? = null
    private val binding get() = _binding!!
    private val moduleMonitor: ModuleMonitor by inject()
    private lateinit var errorAdapter: ErrorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentErrorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeErrors()
    }

    private fun setupRecyclerView() {
        errorAdapter = ErrorAdapter()
        binding.errorRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = errorAdapter
        }
    }

    private fun observeErrors() {
        viewLifecycleOwner.lifecycleScope.launch {
            moduleMonitor.errors.collect { errors ->
                errorAdapter.submitList(errors)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
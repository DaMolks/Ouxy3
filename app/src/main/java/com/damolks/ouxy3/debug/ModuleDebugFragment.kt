package com.damolks.ouxy3.debug

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ModuleDebugFragment : Fragment() {
    private val moduleMonitor: ModuleMonitor by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            moduleMonitor.moduleStates.collect { states ->
                // Update UI with module states
                updateModuleList(states)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            moduleMonitor.errors.collect { errors ->
                // Update error list
                showErrors(errors)
            }
        }
    }

    private fun updateModuleList(states: Map<String, ModuleState>) {
        // Update RecyclerView with module states
    }

    private fun showErrors(errors: List<ModuleError>) {
        // Show error dialog or list
    }
}
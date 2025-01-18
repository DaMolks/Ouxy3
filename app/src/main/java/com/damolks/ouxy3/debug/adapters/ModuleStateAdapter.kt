package com.damolks.ouxy3.debug.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.ItemModuleStateBinding
import com.damolks.ouxy3.debug.ModuleState
import java.time.Instant
import java.time.temporal.ChronoUnit

class ModuleStateAdapter :
    ListAdapter<Pair<String, ModuleState>, ModuleStateAdapter.ViewHolder>(ModuleStateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemModuleStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemModuleStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pair<String, ModuleState>) {
            val (moduleId, state) = item
            binding.apply {
                this.moduleId.text = moduleId

                loadedChip.apply {
                    text = if (state.isLoaded) "Loaded" else "Not Loaded"
                    setChipBackgroundColorResource(
                        if (state.isLoaded) R.color.chip_success else R.color.chip_error
                    )
                }

                activeChip.apply {
                    text = if (state.isActive) "Active" else "Inactive"
                    setChipBackgroundColorResource(
                        if (state.isActive) R.color.chip_success else R.color.chip_warning
                    )
                }

                lastError.apply {
                    isVisible = state.lastError != null
                    text = state.lastError
                }

                lastErrorTime.apply {
                    isVisible = state.lastErrorTimestamp != null
                    text = state.lastErrorTimestamp?.let { formatTimestamp(it) }
                }
            }
        }

        private fun formatTimestamp(timestamp: Instant): String {
            val now = Instant.now()
            val minutes = ChronoUnit.MINUTES.between(timestamp, now)
            return when {
                minutes < 1 -> "Just now"
                minutes < 60 -> "$minutes minutes ago"
                minutes < 1440 -> "${minutes / 60} hours ago"
                else -> "${minutes / 1440} days ago"
            }
        }
    }
}

class ModuleStateDiffCallback : DiffUtil.ItemCallback<Pair<String, ModuleState>>() {
    override fun areItemsTheSame(
        oldItem: Pair<String, ModuleState>,
        newItem: Pair<String, ModuleState>
    ): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<String, ModuleState>,
        newItem: Pair<String, ModuleState>
    ): Boolean {
        return oldItem == newItem
    }
}
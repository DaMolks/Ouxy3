package com.damolks.ouxy3.debug.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damolks.ouxy3.R
import com.damolks.ouxy3.databinding.ItemErrorBinding
import com.damolks.ouxy3.debug.ErrorSeverity
import com.damolks.ouxy3.debug.ModuleError
import java.time.Instant
import java.time.temporal.ChronoUnit

class ErrorAdapter : ListAdapter<ModuleError, ErrorAdapter.ViewHolder>(ErrorDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemErrorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemErrorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(error: ModuleError) {
            binding.apply {
                moduleId.text = error.moduleId
                errorMessage.text = error.error.message ?: "Unknown error"
                timestamp.text = formatTimestamp(error.timestamp)

                severityChip.apply {
                    text = error.severity.name
                    setChipBackgroundColorResource(
                        when (error.severity) {
                            ErrorSeverity.DEBUG -> R.color.chip_debug
                            ErrorSeverity.INFO -> R.color.chip_info
                            ErrorSeverity.WARNING -> R.color.chip_warning
                            ErrorSeverity.ERROR -> R.color.chip_error
                            ErrorSeverity.CRITICAL -> R.color.chip_critical
                        }
                    )
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

class ErrorDiffCallback : DiffUtil.ItemCallback<ModuleError>() {
    override fun areItemsTheSame(oldItem: ModuleError, newItem: ModuleError): Boolean {
        return oldItem.moduleId == newItem.moduleId && oldItem.timestamp == newItem.timestamp
    }

    override fun areContentsTheSame(oldItem: ModuleError, newItem: ModuleError): Boolean {
        return oldItem == newItem
    }
}